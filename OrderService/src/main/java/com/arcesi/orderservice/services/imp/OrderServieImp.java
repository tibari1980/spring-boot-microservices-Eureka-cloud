package com.arcesi.orderservice.services.imp;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.orderservice.dtos.ClientDTO;
import com.arcesi.orderservice.dtos.OrderDTO;
import com.arcesi.orderservice.entities.ClientEntity;
import com.arcesi.orderservice.entities.OrderEntity;
import com.arcesi.orderservice.enums.ErrorsCodeEnumeration;
import com.arcesi.orderservice.enums.EtatOrder;
import com.arcesi.orderservice.exceptions.EntityNotFoundException;
import com.arcesi.orderservice.exceptions.InvalidEntityException;
import com.arcesi.orderservice.external.client.PaymentServiceProxy;
import com.arcesi.orderservice.external.client.ProductServiceProxy;
import com.arcesi.orderservice.external.client.dtos.ProductResponse;
import com.arcesi.orderservice.external.client.requests.TransactionDetailsRequest;
import com.arcesi.orderservice.repositories.ClientRepository;
import com.arcesi.orderservice.repositories.OrderRepository;
import com.arcesi.orderservice.services.OrderService;
import com.arcesi.orderservice.validators.ObjectValidators;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class OrderServieImp implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductServiceProxy productService;
	
	@Autowired
	private PaymentServiceProxy paymentServiceProxy;

	@Autowired
	private ObjectValidators<OrderDTO> orderValidators;

	@Override
	public OrderDTO createOrder(OrderDTO orderDto, Long idClient) {
		log.info("Inside Methode createOrder of OrderServiceImp : OrderDTO : {} , Id client : {} ", orderDto.toString(),
				idClient);
		orderDto.setDateOrder(Instant.now());
		orderDto.setUidOrder(UUID.randomUUID().toString());

		Map<String, String> violations = orderValidators.validate(orderDto);
		if (!violations.isEmpty()) {
			log.error("There are errors while saving order ...... errors: {}", violations);
			throw new InvalidEntityException("There are errors while saving order try again !!!",
					com.arcesi.orderservice.enums.ErrorsCodeEnumeration.ORDER_NOT_VALIDE, violations);
		}

		ProductResponse product = productService.findProductById(orderDto.getIdProduct());

		productService.reduceQuantite(orderDto.getIdProduct(), orderDto.getQuantiteOrder());
		// chek Custumer if exist
		ClientEntity ifClientExist = clientRepository.findById(idClient)
				.orElseThrow(() -> new EntityNotFoundException(
						"Customer with  :` " + idClient + "` not found in our data base .",
						ErrorsCodeEnumeration.CLIENT_NOT_FOUND));
		orderDto.setStatusOrder(EtatOrder.CREATED.getCode());
		orderDto.setMontantOrder(orderDto.getQuantiteOrder() * product.getPrix());
		orderDto.setClientDTO(modelMapper.map(ifClientExist, ClientDTO.class));
		//Saving order
		OrderEntity orderEntity=orderRepository.save(modelMapper.map(orderDto, OrderEntity.class));
		
		log.info("Calling payment to complete Payment......... ");
		TransactionDetailsRequest transactionDetailsRequest=TransactionDetailsRequest.builder()
				.orderId(orderEntity.getCodeOrder())
				.paymentMode(orderDto.getPaymentMode())
				.referenceNumber(UUID.randomUUID().toString())
				.amountOrder(orderEntity.getMontantOrder())
				.build();
		@SuppressWarnings("unused")
		String orderStatus=null;
		try {
			paymentServiceProxy.doPayment(transactionDetailsRequest);
			log.info("Payment done successfully.Changing the oder status to placed");
			orderStatus="PLACED";
		}catch(Exception e) {
			log.error("Error occured in payment.Changing order status to Failed");
			orderStatus="PAYMENT_FAILED";
		}
		//Changing order status 
		orderEntity.setStatusOrder(orderStatus);
		orderRepository.saveAndFlush(orderEntity);
		log.info("Order Created successfully order : {}",orderEntity.toString());
		return modelMapper.map(orderEntity, OrderDTO.class);
	}

}

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
	private ObjectValidators<OrderDTO> orderValidators;

	@Override
	public OrderDTO createOrder(OrderDTO orderDto, Long idClient) {
		log.info("Inside Methode createOrder of OrderServiceImp : OrderDTO : {} , Id client : {} ",
				orderDto.toString(), idClient);
		orderDto.setDateOrder(Instant.now());
		orderDto.setUidOrder(UUID.randomUUID().toString());

		Map<String, String> violations = orderValidators.validate(orderDto);
		if (!violations.isEmpty()) {
			log.error("There are errors while saving order ...... errors: {}", violations);
			throw new InvalidEntityException("There are errors while saving order try again !!!",
					com.arcesi.orderservice.enums.ErrorsCodeEnumeration.ORDER_NOT_VALIDE, violations);
		}

		//chek Custumer if exist
		ClientEntity ifClientExist=clientRepository.findById(idClient).orElseThrow(()->new EntityNotFoundException("Costumer with  :` "+idClient+"` not found in our data base .",ErrorsCodeEnumeration.CLIENT_NOT_FOUND));
		orderDto.setStatusOrder(EtatOrder.CREATED.getCode());
		orderDto.setClientDTO(modelMapper.map(ifClientExist, ClientDTO.class));
		return modelMapper.map(orderRepository.save(modelMapper.map(orderDto, OrderEntity.class)), OrderDTO.class);
	}

}

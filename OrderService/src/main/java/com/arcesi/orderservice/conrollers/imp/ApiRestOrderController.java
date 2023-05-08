package com.arcesi.orderservice.conrollers.imp;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.orderservice.controllers.ApiRestOrder;
import com.arcesi.orderservice.dtos.OrderDTO;
import com.arcesi.orderservice.dtos.responses.OrderResponse;
import com.arcesi.orderservice.services.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v1/orders")
@Slf4j
@Validated
public class ApiRestOrderController implements ApiRestOrder {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ApiRestClientController.class);
	@Autowired
	 private OrderService orderService;
	@Autowired
	private ModelMapper mapper;
	@Override
	public ResponseEntity<OrderResponse> getOrderById(Long idOrder) {
		LOGGER.info("Inside methode getOrderById of ApiRestOrderController  idOrder : {}",idOrder);
		log.info("Inside methode getOrderById of ApiRestOrderController  idOrder : {}",idOrder);
		if(idOrder==null) {
			log.error("IdOrder connot be null");
			return null;
		}
		OrderDTO dto=orderService.getOneOrderById(idOrder);
		return new ResponseEntity<OrderResponse>(mapper.map(dto, OrderResponse.class),HttpStatus.OK);
	}
	@Override
	public ResponseEntity<?> deleteOrderById(Long idOrder) {
		log.info("Inside methode deleteOrderById of ApiRestOrderController  idOrder :{}",idOrder);
		if(null==idOrder) {
			log.error("idOrder cannot be null try again : {}",idOrder);
			return null;
		}
		orderService.deleteOrder(idOrder);
		return new ResponseEntity<String>(String.format("Order  : %s deleted successfully ", idOrder),HttpStatus.OK);
	}

}

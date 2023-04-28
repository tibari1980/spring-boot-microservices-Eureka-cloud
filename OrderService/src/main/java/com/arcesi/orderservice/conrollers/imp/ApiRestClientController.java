package com.arcesi.orderservice.conrollers.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.orderservice.controllers.ApiRestClient;
import com.arcesi.orderservice.dtos.OrderDTO;
import com.arcesi.orderservice.dtos.requests.OrderRequest;
import com.arcesi.orderservice.dtos.responses.OrderResponse;
import com.arcesi.orderservice.services.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/clients/")
@Validated
public class ApiRestClientController implements ApiRestClient {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ModelMapper modelMapper;

	@Override

	public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest, Long idClient) {
		log.info("Inside createOrder OF ApiRestClientController :  orderResponse : {} , Id Client : {}",
				orderRequest.toString(), idClient);
		OrderDTO orderDTO = orderService.createOrder(modelMapper.map(orderRequest, OrderDTO.class), idClient);
		return new ResponseEntity<OrderResponse>(modelMapper.map(orderDTO, OrderResponse.class), HttpStatus.CREATED);
	}

}

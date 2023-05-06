package com.arcesi.orderservice.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arcesi.orderservice.dtos.requests.OrderRequest;
import com.arcesi.orderservice.dtos.responses.OrderResponse;

import jakarta.validation.constraints.Positive;

public interface ApiRestClient {
	
	@PostMapping(value="{idClient}/orders",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderResponse> createOrder(@RequestBody final OrderRequest orderRequest,
			@PathVariable(name = "idClient") @Positive(message = "Client Id must be greater than zero.") Long idClient);

}

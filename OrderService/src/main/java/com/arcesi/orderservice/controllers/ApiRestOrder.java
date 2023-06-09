package com.arcesi.orderservice.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.arcesi.orderservice.dtos.responses.OrderResponse;

import jakarta.validation.constraints.Positive;

public interface ApiRestOrder {

	
	@GetMapping(value="/findOrderById/{idOrder}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable(name="idOrder") @Positive(message = "Order Id must be greater than zero")   Long idOrder);

	@DeleteMapping(value="/{idOrder}")
	public ResponseEntity<String> deleteOrderById(@PathVariable(name="idOrder") @Positive(message = "Order Id : must be greater than zero") Long idOrder);
}

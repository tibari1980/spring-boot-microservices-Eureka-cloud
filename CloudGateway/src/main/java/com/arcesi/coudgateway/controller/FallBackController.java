package com.arcesi.coudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

	@GetMapping(value="/orderServiceFallBack")
	public String orderServiceFallBack() {
		return "Order service is down!!";
	}
	
	@GetMapping(value="/paymentServiceFallBack")
	public String paymentServiceFallBack() {
		return "Payment service is down!";
	}
	
	@GetMapping(value="/productServiceFallBack")
	public String productServiceFallBack() {
		return "Product Service is down!";
	}
	@GetMapping(value="/categoriesServiceFallBack")
	public String categoryServiceFallBack() {
		return "Category Service is down";
	}
}

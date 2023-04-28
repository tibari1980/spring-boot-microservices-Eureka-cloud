package com.arcesi.orderservice.conrollers.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.orderservice.controllers.ApiRestOrder;
import com.arcesi.orderservice.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/orders/")
@RequiredArgsConstructor
public class ApiRestOrderController implements ApiRestOrder {

	@Autowired
	private OrderService orderService;
}

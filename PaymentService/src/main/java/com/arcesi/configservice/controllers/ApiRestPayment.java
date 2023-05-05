package com.arcesi.configservice.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arcesi.configservice.dtos.requests.TransactionDetailsRequest;

public interface ApiRestPayment {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> doPayment(@RequestBody TransactionDetailsRequest paymentRequest);

}

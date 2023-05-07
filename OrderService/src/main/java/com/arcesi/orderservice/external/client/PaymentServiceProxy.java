package com.arcesi.orderservice.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arcesi.orderservice.external.client.requests.TransactionDetailsRequest;


@FeignClient(name="payment-service/api/v1/payments")
public interface PaymentServiceProxy {

	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> doPayment(@RequestBody TransactionDetailsRequest paymentRequest);
}

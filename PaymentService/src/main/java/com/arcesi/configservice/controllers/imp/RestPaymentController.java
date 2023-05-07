package com.arcesi.configservice.controllers.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.configservice.controllers.ApiRestPayment;
import com.arcesi.configservice.dtos.TransactionDetailsDTO;
import com.arcesi.configservice.dtos.requests.TransactionDetailsRequest;
import com.arcesi.configservice.dtos.responses.TransactionDetailsResponse;
import com.arcesi.configservice.services.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v1/payments")
@Slf4j
@Validated
public class RestPaymentController implements ApiRestPayment {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private ModelMapper mapper;

	@Override
	public ResponseEntity<Long> doPayment(TransactionDetailsRequest paymentRequest) {
		log.info("Inside methode doPayment of ApiRestPayment TransctionDetails : {}", paymentRequest.toString());
		TransactionDetailsDTO dtoTransaction = paymentService
				.doPayment(mapper.map(paymentRequest, TransactionDetailsDTO.class));
		return new ResponseEntity<Long>(dtoTransaction.getCode(), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<TransactionDetailsResponse> getPaymentDetailsByOrderId(Long idOrder) {
		log.info("Inside methode getPaymentDetailsByOrderId of RestPaymentController idOrder : {} ",idOrder);
		if(null==idOrder) {
			log.error("IdOrder cannot be null");
			return null;
		}
		TransactionDetailsDTO dto=paymentService.getPaymentDetailsByOrderId(idOrder);
		return new ResponseEntity<TransactionDetailsResponse>(mapper.map(dto,TransactionDetailsResponse.class),HttpStatus.OK);
	}

}

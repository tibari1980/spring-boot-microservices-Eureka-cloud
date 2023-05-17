package com.arcesi.configservice.jddtest;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.arcesi.configservice.entities.TransactionDetails;
import com.arcesi.configservice.repositories.TransactionDetailsRepository;

@Component
public class JeuDDTest implements CommandLineRunner {

	@Autowired
	private TransactionDetailsRepository detailsRepository;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 1; i < 0; i++) {
			TransactionDetails bean = TransactionDetails.builder().referenceNumber(UUID.randomUUID().toString())
					.orderAmount(2500).payementDate(Instant.now()).modePayment("CREDIT_CARD")
					.statusPayment("CREATED").orderId(i + 1)
					.build();

			detailsRepository.save(bean);
		}
	}

}

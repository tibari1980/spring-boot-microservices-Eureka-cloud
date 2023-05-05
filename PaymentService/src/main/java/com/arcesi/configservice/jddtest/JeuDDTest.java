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
		for (int i = 0; i < 30; i++) {
			TransactionDetails bean = TransactionDetails.builder().referenceNumber(UUID.randomUUID().toString())
					.amountOrder(i + 5 * 20).payementDate(Instant.now()).paymentMode("CREDIT_CARD")
					.paymentStatus("VALIDEE").orderId(i + 1)
					.build();

			detailsRepository.save(bean);
		}

		System.out.println("hello fin test ");
	}

}

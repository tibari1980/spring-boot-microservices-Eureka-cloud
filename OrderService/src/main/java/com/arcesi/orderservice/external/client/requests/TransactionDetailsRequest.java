package com.arcesi.orderservice.external.client.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TransactionDetailsRequest {

	private long orderId;
	private String modePayment;
	private String referenceNumber;
	private double orderAmount;

}

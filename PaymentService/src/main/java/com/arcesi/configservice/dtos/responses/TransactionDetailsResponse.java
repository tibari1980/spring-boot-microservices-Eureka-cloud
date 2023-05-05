package com.arcesi.configservice.dtos.responses;

import java.time.Instant;

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
public class TransactionDetailsResponse {

	private Long code;
	private long orderId;
	private String paymentMode;
	private String referenceNumber;
	private Instant payementDate;
	private String paymentStatus;
	private double amountOrder;
	private Instant updatedAt;

}

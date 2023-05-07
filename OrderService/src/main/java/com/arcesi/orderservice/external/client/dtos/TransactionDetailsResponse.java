package com.arcesi.orderservice.external.client.dtos;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private long orderId;
	private String modePayment;
	private String referenceNumber;
	private Instant payementDate;
	private String statusPayment;
	private double orderAmount;
	private Instant updatedAt;

}

package com.arcesi.configservice.dtos.requests;

import com.arcesi.configservice.enums.PaymentModeEnum;

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
	private String paymentMode;
	private String referenceNumber;
	private double amountOrder;

}

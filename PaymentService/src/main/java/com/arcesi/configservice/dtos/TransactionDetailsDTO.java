package com.arcesi.configservice.dtos;

import java.time.Instant;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class TransactionDetailsDTO {

	 
	private Long code;
	@Min(value = 1, message = "Order should not be less than 1 .")
 	private long orderId;
	@NotBlank(message = "Payment mode must not be empty.")
 	private String modePayment;
	@NotBlank(message = "Reference number must not be empty.")
 	private String referenceNumber;
 	private Instant payementDate;
 	private String statusPayment;
 	@Min(value = 1, message = "Amount  should not be less than 1 .")
	@Max(value = 5000,message="Amount should not be greater than 50000")
 	private double orderAmount;
 	private Instant updatedAt;
	
}

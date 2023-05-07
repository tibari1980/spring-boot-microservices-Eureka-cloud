package com.arcesi.configservice.entities;

import java.time.Instant;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="TRANSACTIONS_DETAILS")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CODE_TRANSACTION",insertable = true,updatable = false,nullable = false,unique = true)
	private Long code;
	@Column(name="ORDER_ID",nullable = false,insertable = true,updatable = true)
	private long orderId;
	@Column(name="PAYMENT_MODE",insertable = true,nullable = false)
	private String paymentMode;
	@Column(name="REFERENCE_NUMBER",insertable = true,nullable = false)
	private String referenceNumber;
	@Column(name="PAYMENT_DATE",insertable = true,updatable = true)
	private Instant payementDate;
	@Column(name="PAYMENT_STATUS",insertable = true,updatable = true)
	private String paymentStatus;
	@Column(name="AMOUNT")
	private double amountOrder;
	@LastModifiedDate
	@Column(name="UPDATED_AT",insertable = true,updatable = true,nullable = true)
	private Instant updatedAt;
	
}

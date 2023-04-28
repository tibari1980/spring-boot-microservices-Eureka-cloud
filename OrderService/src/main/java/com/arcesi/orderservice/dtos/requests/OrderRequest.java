package com.arcesi.orderservice.dtos.requests;

import java.io.Serializable;
import java.time.Instant;

import com.arcesi.orderservice.enums.PaymentModeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Long codeOrder;
	@JsonIgnore
	private String uidOrder;

	private Long idProduct;

	@JsonIgnore
	private String statusOrder;
	@JsonIgnore
	private Instant dateOrder;
	private int quantiteOrder;

	private double montantOrder;
	private PaymentModeEnum paymentMode;
	@JsonIgnore
	private ClientRequest clientRequest;

}

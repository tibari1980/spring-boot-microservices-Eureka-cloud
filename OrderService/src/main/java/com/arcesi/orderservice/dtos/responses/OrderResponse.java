package com.arcesi.orderservice.dtos.responses;

import java.io.Serializable;
import java.time.Instant;

import com.arcesi.orderservice.dtos.ClientDTO;
import com.arcesi.orderservice.external.client.dtos.ProductResponse;
import com.arcesi.orderservice.external.client.dtos.TransactionDetailsResponse;
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
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse implements Serializable{



	private static final long serialVersionUID = 1L;
	private Long codeOrder;
	private String uidOrder;
	@JsonIgnore
	private Long idProduct;
	private String statusOrder;
	private Instant dateOrder;
	private int quantiteOrder;
	private double montantOrder;
	private String paymentMode;
	private ClientDTO clientDTO;
	

	private ProductResponse productDetails;
	private TransactionDetailsResponse transactionDetails;




}

package com.arcesi.orderservice.dtos.requests;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long codeOrder;
	private String uidOrder;
	private Long idProduct;
	private String statusOrder;
	private Instant dateOrder;
	private int quantiteOrder;
	private double montantOrder;
	private ClientRequest clientRequest;

}

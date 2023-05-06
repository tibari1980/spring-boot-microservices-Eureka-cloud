package com.arcesi.orderservice.dtos;

import java.io.Serializable;
import java.time.Instant;

import com.arcesi.orderservice.external.client.dtos.ProductResponse;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codeOrder;
	private String uidOrder;
	@Min(value = 1, message = "Product id should not be less 1")
	private Long idProduct;
	private String statusOrder;
	@NotNull
	private Instant dateOrder;
	@Min(value = 1, message = "Quantite order should be not  less 1 euro")
	private int quantiteOrder;
	private double montantOrder;
	private ClientDTO clientDTO;
	@NotBlank(message = "Payement mode is mandatory.")
	private String paymentMode;
	
	private ProductResponse productResponse;

}

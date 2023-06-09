package com.arcesi.orderservice.external.client.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class ProductResponse extends AbstractEntityResponse {

	private static final long serialVersionUID = 1L;

	private Long codeProduct;
	private String uidProduct;
	private String designation;
	private String description;
	private double prix;
	private int quantiteStock;

	private Boolean isDisponible;
	private Boolean isEnPromotion;
	private String photo;

	private CategoryResponse categoryResponse;
}

package com.arcesi.ProductService.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ProductRequest extends AbstractEntityRequest {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Long codeProduct;
	@JsonIgnore
	private String uidProduct;
	private String designation;
	private String description;
	private double prix;
	private int quantiteStock;

	private Boolean isDisponible;
	private Boolean isEnPromotion;
	private String photo;
	@JsonIgnore
	private CategoryRequest categoryRequest;

}

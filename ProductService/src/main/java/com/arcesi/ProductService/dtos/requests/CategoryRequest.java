package com.arcesi.ProductService.dtos.requests;

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
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest extends AbstractEntityRequest {

	private static final long serialVersionUID = 1L;
	 
	 
	private String libelle;
	private String description;

}

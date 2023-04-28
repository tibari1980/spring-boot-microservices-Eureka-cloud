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
public class CategoryResponse extends AbstractEntityResponse {

	
	
	private static final long serialVersionUID = 1L;	 
	private Long codeCategory;
 	private String uidCategory;
 	private String libelle;
	private String description;
	
	
}

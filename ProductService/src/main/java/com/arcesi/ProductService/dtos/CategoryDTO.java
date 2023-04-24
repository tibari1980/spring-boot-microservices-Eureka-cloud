package com.arcesi.ProductService.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class CategoryDTO extends AbstractEntityDTO {

	private static final long serialVersionUID = 1L;
	private Long codeCategory;
	private String uidCategory;
	@NotBlank(message = "Libelle Category must not be empty.")
	@Size(min = 4,max = 40,message ="Libelle Category must be between 4 to 40 characters")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$",message = "Designation require alphanumiric and space .")
	private String libelle;
	
	@NotBlank(message = "Description must not be empty.")
	@Size(min = 4,message ="Description must be between 4 to 1200 characters")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$",message = "Description require alphanumiric and space .")
	private String description;

}

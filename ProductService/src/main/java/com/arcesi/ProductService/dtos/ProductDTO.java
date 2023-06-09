package com.arcesi.ProductService.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class ProductDTO extends AbstractEntityDTO {

	private static final long serialVersionUID = 1L;

	private Long codeProduct;
	private String uidProduct;
	
	@NotBlank(message = "Designation must not be empty.")
	@Size(min = 4,max = 40,message ="Designation must be between 4 to 40 characters")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$",message = "Designation require alphanumiric and space .")
	private String designation;
	
	@NotBlank(message = "Description must not be empty.")
	@Size(min = 4,message ="Description must be between 4 to 1200 characters")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$",message = "Description require alphanumiric and space .")
	private String description;
	
	
	@Min(value = 1, message = "Price should not be less than 1 .")
	@Max(value = 5000,message="Price should not be greater than 5000")
	private double prix;
	
	@Min(value = 0, message = "Quantite should not be less than 0")
	@Max(value = 5000,message="Quantite should not be greater than 1000")
	private int quantiteStock;

	private Boolean isDisponible;
	private Boolean isEnPromotion;
	
	@NotBlank(message = "Photo must not be empty.")
	private String photo;
	private CategoryDTO categoryDTO;

}

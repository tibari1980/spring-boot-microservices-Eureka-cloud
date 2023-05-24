package com.arcesi.identityservice.dtos;

import java.io.Serializable;
import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class RoleDTO implements Serializable{

	
	 
	private static final long serialVersionUID = 1L;
	
	 
	private Long codeRole;
 	private String codeUniqueRole;
 	@NotBlank(message = "Role name  must not be empty.")
	@Size(min = 4,max = 40,message ="Role name must have a minimum of 5 characters and a maximum of 40 characters.")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$",message = "Role name require alphanumiric and space .")
 	private String roleName;
 	private Instant createdAt;
 	private Instant updatedAt;
	 
	
	
	

}

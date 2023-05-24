package com.arcesi.identityservice.security.auth;

 
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	@JsonIgnore
	private Long id;
	
	@JsonIgnore
	private String uidUser;
 	
	private String firstName;
 	private String lastName;
 	private String email;
 	private String password;
 	
}

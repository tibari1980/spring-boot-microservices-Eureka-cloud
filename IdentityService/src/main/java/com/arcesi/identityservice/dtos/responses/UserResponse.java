package com.arcesi.identityservice.dtos.responses;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

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
public class UserResponse implements Serializable{
	
	private static final long serialVersionUID = -6515559816600750171L;
	 
	private Long id;
 	private String uidUser;
 	private String firstName;
 	private String lastName;
 	private String email;
 	private String password;
	private Collection<RoleResponse> roleBeans;
 	private Boolean locked;
 	private Boolean enabled ;
 	private String ipAdresse;
 	private Instant createdAt;
 	private Instant updatedAt;

	
	 

	 

	 

}

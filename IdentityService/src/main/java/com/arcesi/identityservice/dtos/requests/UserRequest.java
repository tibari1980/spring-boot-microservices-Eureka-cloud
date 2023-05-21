package com.arcesi.identityservice.dtos.requests;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class UserRequest implements Serializable {
	
	private static final long serialVersionUID = -6515559816600750171L;
	
	@JsonIgnore
	private Long id;
	
	@JsonIgnore
	private String uidUser;
 	
	private String firstName;
 	private String lastName;
 	private String email;
 	private String password;
 	@JsonIgnore
	private Collection<RoleRequest> roleRequests;
 	
 	@JsonIgnore
 	private Boolean locked;
 	
 	@JsonIgnore
 	private Boolean enabled ;
 	@JsonIgnore
 	private String ipAdresse;
 	@JsonIgnore
 	private Instant createdAt;
 	@JsonIgnore
 	private Instant updatedAt;

	
	 

	 

	 

}

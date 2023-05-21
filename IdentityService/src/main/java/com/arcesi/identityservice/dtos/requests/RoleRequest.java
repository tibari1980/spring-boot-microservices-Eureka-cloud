package com.arcesi.identityservice.dtos.requests;

import java.io.Serializable;
import java.time.Instant;

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
public class RoleRequest implements Serializable{

	
	 
	private static final long serialVersionUID = 1L;
	
	 
	@JsonIgnore
	private Long codeRole;
	@JsonIgnore
	private String codeUniqueRole;
 	private String roleName;
 	@JsonIgnore
 	private Instant createdAt;
 	@JsonIgnore
 	private Instant updatedAt;
	 
	
	
	

}

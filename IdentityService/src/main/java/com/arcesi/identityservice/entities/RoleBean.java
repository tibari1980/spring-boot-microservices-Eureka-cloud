package com.arcesi.identityservice.entities;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name = "ROLE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_ROLE", name = "CODE_ROLE_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_ROLE_UNIQUE", name = "CODE_ROLE_UNIQUE_SEQUENCE") })

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class RoleBean implements Serializable {

	
	 
	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(allocationSize = 1, sequenceName = "role_sequence", name = "role_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
	@Column(name = "CODE_ROLE", nullable = false, unique = true)
	private Long codeRole;
	
	@Column(name = "CODE_ROLE_UNIQUE", length = 40, unique = true, nullable = false)
	private String codeUniqueRole;
	
	
	@Column(name="ROLE_NAME",unique = true,nullable = false,insertable = true,updatable = true)
	private String roleName;
	
	@CreatedDate
	@Column(name="CREATED_AT",nullable = false,insertable = true,updatable = false)
	private Instant createdAt;
	
	@LastModifiedDate
	@Column(name="UPDATED_AT",updatable = true,nullable = true)
	private Instant updatedAt;
	
	 
	
	
	

}

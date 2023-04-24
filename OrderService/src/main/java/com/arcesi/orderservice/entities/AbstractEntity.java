package com.arcesi.orderservice.entities;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AbstractEntity implements Serializable {

	
	 
	private static final long serialVersionUID = 1L;
	@CreatedDate
	@Column(name="CREATED_AT",nullable = false,updatable = false)
	private Instant createAt;
	@LastModifiedDate
	@Column(name="UPDATED_AT",nullable = false,updatable = true)
	private Instant updatedAt;
}

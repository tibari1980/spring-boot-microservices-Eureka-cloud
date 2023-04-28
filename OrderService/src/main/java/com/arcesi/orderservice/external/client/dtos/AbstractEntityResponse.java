package com.arcesi.orderservice.external.client.dtos;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public class AbstractEntityResponse implements Serializable {
	 
	
	
	private static final long serialVersionUID = 1L;

	private Instant createdAt;

	private Instant updatedAt;

}

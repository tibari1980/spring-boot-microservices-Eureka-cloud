package com.arcesi.ProductService.dtos.requests;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class AbstractEntityRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Instant createdAt;

	@JsonIgnore
	private Instant updatedAt;

}

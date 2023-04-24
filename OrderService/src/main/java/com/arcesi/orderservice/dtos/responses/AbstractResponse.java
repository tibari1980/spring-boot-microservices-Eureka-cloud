package com.arcesi.orderservice.dtos.responses;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public class AbstractResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Instant createdAt;

	private Instant updatedAt;
}

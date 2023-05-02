package com.arcesi.orderservice.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.orderservice.external.client.dtos.ProductResponse;

import jakarta.validation.constraints.Positive;

@FeignClient(name = "product-service/api/v1/products/")
public interface ProductServiceProxy {

	@PutMapping(value = "/reduceQuantite/{idProduct}")
	Void reduceQuantite(
			@PathVariable(name = "idProduct") @Positive(message = "Product ID must be greater than zero") Long idProduct,
			@RequestParam(defaultValue = "0", name = "quantity") int quantity);

	@GetMapping(value = "findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ProductResponse findProductById(
			@PathVariable(name = "id") @Positive(message = "Product ID must be greater than zero") Long id);
}

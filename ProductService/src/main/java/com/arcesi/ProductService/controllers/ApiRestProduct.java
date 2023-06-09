package com.arcesi.ProductService.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.ProductService.dtos.requests.ProductRequest;
import com.arcesi.ProductService.dtos.responses.ProductResponse;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public interface ApiRestProduct {

	//http://localhost:8087/api/v1/products/all?partialDesignation=ordi
	
	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductResponse>> getAllProducts(
			@RequestParam(name = "partialDesignation", defaultValue = "", required = false) String partialDesignation,
			@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);
	
	//http://localhost:8087/api/v1/products/findById/1
	
	@GetMapping(value="findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> findProductById(@PathVariable(name="id") @Positive(message = "Product ID must be greater than zero")  Long id);

	//689bf997-59b0-4485-9823-8ec7c99ae853
	
	@GetMapping(value="findByUid/{uid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> findProductByUid(@PathVariable(name="uid") @Size(min = 36,max=36 ,message = "Category UID shoud have 36 characters.")  String uid);
	
	@GetMapping(value="findByDesignation/{designation}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> findProductByDesignation(@PathVariable(name="designation") @NotBlank @NotNull @NotEmpty String designation);
	
	
	
	//http://localhost:8087/api/v1/products/findAllProductByCategoryId/1
	
	@GetMapping(value = "/findAllProductByCategoryId/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductResponse>> getAllProductByCategory(
			@PathVariable(name = "codeCategory") @Positive(message = "Category ID must be greater than zero") Long codeCategory,
			@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);
	
	
	//http://localhost:8087/api/v1/products/findProductDisponiblreEnPromotionPrixUnitaireByCategory/2?prix=500
		
	@GetMapping(value="/findProductDisponiblreEnPromotionPrixUnitaireByCategory/{codeCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductResponse>> findAllProductByCategoryDisponibleAndEnPromotionAndPrix(
			@RequestParam(name = "isDisponible", defaultValue = "true") Boolean isDisponible,
			@RequestParam(name = "isPromotion", defaultValue = "true") Boolean isPromotion,
			@RequestParam(name = "prix", defaultValue = "0") @Positive(message = "Product PRICE must be greater than zero") String prix,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit,@PathVariable("codeCategory") @Positive(message = "Category ID must be greater than zero") Long codeCategory);
	
	
	
     	//http://localhost:8087/api/v1/products/1
	
		@PutMapping(value = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest,
				@PathVariable("idProduct") @Positive(message = "Product ID must be greater than zero") Long idProduct);
		
         //	http://localhost:8087/api/v1/products/1
		
		@DeleteMapping(value="{idProduct}")
		public ResponseEntity<Object> deleteProduct( @PathVariable(value="idProduct") @Min(1) @Positive(message = "Product ID must be greater than zero") Long idProduct);
		
		
		@PutMapping(value = "/reduceQuantite/{idProduct}")
		public ResponseEntity<Void> reduceQuantite(@PathVariable(name="idProduct") @Positive(message = "Product ID must be greater than zero") Long idProduct,@RequestParam(defaultValue = "0",name = "quantity") int quantity);

		
}

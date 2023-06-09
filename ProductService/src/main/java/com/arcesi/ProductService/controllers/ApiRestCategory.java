package com.arcesi.ProductService.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.ProductService.dtos.requests.CategoryRequest;
import com.arcesi.ProductService.dtos.requests.ProductRequest;
import com.arcesi.ProductService.dtos.responses.CategoryResponse;
import com.arcesi.ProductService.dtos.responses.ProductResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public interface ApiRestCategory {

	// Status : 200 OK
	// http://localhost:8087/api/v1/categories/all?partialLibelle=vre
	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryResponse>> getAllCategories(
			@RequestParam(name = "partialLibelle", defaultValue = "", required = false) String partialLibelle,
			@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);

	// http://localhost:8080/api/v1/categories/5/products

	@PostMapping(value = "{categoryId}/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest,
			@PathVariable(name = "categoryId") @Positive(message = "Category ID must be greater than zero") Long categoryId);

	// http://localhost:8087/api/v1/categories/
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest);

	// http://localhost:8087/api/v1/categories/findById/1

	@GetMapping(value = "findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> findCategoryById(
			@PathVariable(name = "id") @Positive(message = "Category ID must be greater than zero") Long id);

	// http://localhost:8087/api/v1/categories/findByUid/def4ee8c-0225-4bb6-be43-2b2c7a457171

	@GetMapping(value = "findByUid/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> findCategoryByUid(
			@PathVariable(name = "uid") @NotBlank(message = "Cateogy UID must be blank") String uid);

	@PutMapping(value = "/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest catRequest,
			@PathVariable("idCategory") Long idCategory);

	@DeleteMapping(value = "{idCategory}")
	public ResponseEntity<String> deleteCategory(
			@PathVariable(value = "idCategory") @Positive(message = "Category ID must be greater than zero") Long idCategory);

	@DeleteMapping(value = "deleteAllCategory")
	public ResponseEntity<String> deleteAllCategories();
}
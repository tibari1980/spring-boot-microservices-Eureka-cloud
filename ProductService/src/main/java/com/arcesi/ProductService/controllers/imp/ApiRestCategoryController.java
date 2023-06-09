package com.arcesi.ProductService.controllers.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.ProductService.controllers.ApiRestCategory;
import com.arcesi.ProductService.dtos.CategoryDTO;
import com.arcesi.ProductService.dtos.ProductDTO;
import com.arcesi.ProductService.dtos.requests.CategoryRequest;
import com.arcesi.ProductService.dtos.requests.ProductRequest;
import com.arcesi.ProductService.dtos.responses.CategoryResponse;
import com.arcesi.ProductService.dtos.responses.ProductResponse;
import com.arcesi.ProductService.enums.ErrorsCodeEnumeration;
import com.arcesi.ProductService.exceptions.ArgumentNotValidException;
import com.arcesi.ProductService.services.CategoryService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(value = "/api/v1/categories/")
@Slf4j
@Validated
public class ApiRestCategoryController implements ApiRestCategory {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest, Long categoryId) {
		log.info("Inside méthode createProduct in Controller CategoryRestController  product: {} ,identifiant category : {}",productRequest.toString(), categoryId);
		ProductDTO productDTO=categoryService.createProduct(modelMapper.map(productRequest, ProductDTO.class),categoryId);
		return new ResponseEntity<ProductResponse>(modelMapper.map(productDTO, ProductResponse.class),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
		log.info("Inside méthode createCategory in Controller CategoryRestController  Category : {} ",categoryRequest.toString());
		CategoryDTO categoryDTO=categoryService.createCategory(modelMapper.map(categoryRequest, CategoryDTO.class));
		return new ResponseEntity<CategoryResponse>(modelMapper.map(categoryDTO, CategoryResponse.class),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CategoryResponse> findCategoryById(Long id) {
		log.info("Inside methode findCategoryById of ApiRestCategoryController  code Categoyr : {} ", id);

		if (null==id) {
			log.error("Code Category is not valide try again : {}", id);
			throw new ArgumentNotValidException("Code Category : `" + id + "` is not valid try again",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}
		CategoryDTO catDTO = categoryService.getCategoryById(id);

		return new ResponseEntity<CategoryResponse>(modelMapper.map(catDTO, CategoryResponse.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoryResponse> findCategoryByUid(String uid) {
		log.info("Inside methode findCategoryByUid of ApiRestCategoryController  uid Categoyr : {} ", uid);

		if (StringUtils.isBlank(uid)) {
			log.error("Uid Category is not valide try again : {}", uid);
			throw new ArgumentNotValidException("Uid Category : `" + uid + "` is not valid try again",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}
		CategoryDTO catDTO = categoryService.getCategoryByUid(uid);

		return new ResponseEntity<CategoryResponse>(modelMapper.map(catDTO, CategoryResponse.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoryResponse> updateCategory(CategoryRequest catRequest, Long idCat) {
		log.info(
				"Inside methode updateCategory in Controller CategoryRestController  categoryRequest : {}  , idCategory : {} ",
				catRequest.toString(), idCat);
		CategoryDTO catDTO = categoryService.updateCategory(modelMapper.map(catRequest, CategoryDTO.class), idCat);

		return new ResponseEntity<CategoryResponse>(modelMapper.map(catDTO, CategoryResponse.class),
				HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<List<CategoryResponse>> getAllCategories(String partialLibelle, int page, int limit) {
		log.info(
				"Inside methode getAllCategories of ApiRestProductController   Partial libelle : {} , page : {} ,limi :{}",
				partialLibelle, page, limit);
		List<CategoryDTO> lstCateogoriesDtos = categoryService.getAllCategoriesByLibelleContaining(partialLibelle, page,
				limit);
		if (lstCateogoriesDtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<CategoryResponse> lstCategoriesResponse = lstCateogoriesDtos.stream()
				.map(c -> modelMapper.map(c, CategoryResponse.class)).collect(Collectors.toList());
		return new ResponseEntity<List<CategoryResponse>>(lstCategoriesResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteCategory(Long idCategory) {
		log.info("Inside methode deleteCategory of ApiRestCategoryController  code Category : {}", idCategory);
		if (null==idCategory) {
			log.info("Code category `{}` not valid try again", idCategory);
			throw new ArgumentNotValidException("Code category : `" + idCategory + "` not valid try again",
					ErrorsCodeEnumeration.CATEGORY_NOT_FOUND);
		}
		categoryService.deleteCategoryById(idCategory);
		return new ResponseEntity<String>("Category with id  :`" + idCategory + "`deleted successufully ", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteAllCategories() {
		log.info("Inside methode deleteAllCategories of ApiRestCategoryController.");
		categoryService.deleteAllCategories();
	   return new ResponseEntity<String>("All Categories are deleted successufully.", HttpStatus.OK);
	}

}

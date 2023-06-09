package com.arcesi.ProductService.controllers.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.ProductService.controllers.ApiRestProduct;
import com.arcesi.ProductService.dtos.ProductDTO;
import com.arcesi.ProductService.dtos.requests.ProductRequest;
import com.arcesi.ProductService.dtos.responses.ProductResponse;
import com.arcesi.ProductService.enums.ErrorsCodeEnumeration;
import com.arcesi.ProductService.exceptions.ArgumentNotValidException;
import com.arcesi.ProductService.services.ProductService;

import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:8086")
@RestController
@RequestMapping(value = "/api/v1/products/")
@Slf4j
@Validated
public class ApiRestProductController implements ApiRestProduct {

	@Autowired
	private ProductService productService;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<List<ProductResponse>> getAllProducts(String partialDesignation, int page, int limit) {
		log.info(
				"Inside methode getAllProducts of ApiRestProductController   Partial Designation : {} , page : {} ,limi :{}",
				partialDesignation, page, limit);
		List<ProductDTO> lstProductDtos = productService.getAllProductByDesignationContaining(partialDesignation, page,
				limit);
		if (lstProductDtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<ProductResponse> lstProductsResponse = lstProductDtos.stream()
				.map(p -> modelMapper.map(p, ProductResponse.class)).collect(Collectors.toList());
		return new ResponseEntity<List<ProductResponse>>(lstProductsResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductResponse> findProductById(Long id) {
		log.info("Inside methode findById of ApiRestProductController  code product : {} ", id);

		if (id==null) {
			log.error("Code Product is not valide try again : {}", id);
			throw new ArgumentNotValidException("Code Product : `" + id + "` is not valid try again",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}
		ProductDTO productDTO = productService.getProductById(id);

		return new ResponseEntity<ProductResponse>(modelMapper.map(productDTO, ProductResponse.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductResponse> findProductByUid(String code) {
		log.info("Inside methode findByUid of ApiRestProductController  code product : {} ", code);

		if (StringUtils.isBlank(code)) {
			log.error("Code Product is not valide try again : {}", code);
			throw new ArgumentNotValidException("Code Product : `" + code + "` is not valid try again",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}
		ProductDTO productDTO = productService.getProductByUid(code);

		return new ResponseEntity<ProductResponse>(modelMapper.map(productDTO, ProductResponse.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductResponse> findProductByDesignation(String designation) {
		log.info("Inside methode findProductByDesignation of ApiRestProductController  Designation product : {} ",
				designation);

		if (StringUtils.isBlank(designation) || StringUtils.isNumeric(designation)) {
			log.error("Designation Product is not valide try again : {}", designation);
			throw new ArgumentNotValidException("Designation Product : `" + designation + "` is not valid try again",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}
		ProductDTO productDTO = productService.getProductByDesignation(designation);

		return new ResponseEntity<ProductResponse>(modelMapper.map(productDTO, ProductResponse.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProductResponse>> getAllProductByCategory(Long codeCategory, int page, int limit) {
		log.info(
				"Inside method getAllProductByCategory in Contorlller ProductRestController page : {} , limi : {} , code Category : {}",
				page, limit, codeCategory);
		if (codeCategory==null) {
			log.error("Cade Category is not valid try again  : {}", codeCategory);
			throw new ArgumentNotValidException("Code category :`" + codeCategory + "` is not valid try again ",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}
		List<ProductDTO> lsDtos = productService.getAllProductByCategoryId(codeCategory, page, limit);
		List<ProductResponse> lstResponses = lsDtos.stream().map(pr -> modelMapper.map(pr, ProductResponse.class))
				.collect(Collectors.toList());
		if (lsDtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductResponse>>(lstResponses, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProductResponse>> findAllProductByCategoryDisponibleAndEnPromotionAndPrix(
			Boolean isDisponible, Boolean isPromotion, String prixUnitaire, int page, int limit, Long codeCategory) {
		log.info(
				"Inside methode findAllProductByCategoryDisponibleAndEnPromotionAndPrix in Controller  ProductRestController Disponible : {} , Promotion : {} , Prix unitaire : {} , Page : {} , Limit : {} , Cade Category : {} ",
				isDisponible, isPromotion, prixUnitaire, page, limit, codeCategory);
		if (codeCategory==null) {
			log.error("Code Category is not valid try again  : {}", codeCategory);
			throw new ArgumentNotValidException("Code category :` " + codeCategory + "` is not valid try again ",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}

		List<ProductDTO> lsDtos = productService.getAllProductDisponibleEnPromotionAvecPrixCategory(isDisponible,
				isPromotion, Double.parseDouble(prixUnitaire), page, limit, codeCategory);
		List<ProductResponse> lstResponses = lsDtos.stream().map(dto -> modelMapper.map(dto, ProductResponse.class))
				.collect(Collectors.toList());
		if (lsDtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductResponse>>(lstResponses, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductResponse> updateProduct(ProductRequest productRequest, Long idProduct) {
		log.info(
				"Inside methode updateProduct in Controller ProductRestController  productRequest : {}  , idProduct : {} ",
				productRequest, idProduct);
		ProductDTO productDTO = productService.updateProduct(modelMapper.map(productRequest, ProductDTO.class), idProduct);

		return new ResponseEntity<ProductResponse>(modelMapper.map(productDTO, ProductResponse.class),
				HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Object> deleteProduct(Long idProduct) {
		log.info("Inside methode deleteProduct of ApiRestProductController  code Product : {}", idProduct);
		if (idProduct==null) {
			log.info("Code product `{}` not valid try again", idProduct);
			throw new ArgumentNotValidException("Code product : `" + idProduct + "` not valid try again",
					ErrorsCodeEnumeration.PRODUCT_NOT_FOUND);
		}
		productService.deleteById(idProduct);
		return new ResponseEntity<Object>("Product with id :`" + idProduct + "`deleted successfully  ", HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Void> reduceQuantite(
			@Positive(message = "Product ID must be greater than zero") Long idProduct, int quantity) {
		log.info("Inside methode reduceQuantite of ApiRestProductController  Product id : {} , Quantite :{}",idProduct,quantity);
		
		productService.reduceQuantite(idProduct,quantity);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

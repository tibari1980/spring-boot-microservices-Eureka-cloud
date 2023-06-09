package com.arcesi.ProductService.services;

import java.util.List;

import com.arcesi.ProductService.dtos.ProductDTO;

public interface ProductService {

	List<ProductDTO> getAllProductByDesignationContaining(String partialDesignation, int page, int limit);

	ProductDTO getProductById(final Long idProduct);

	ProductDTO getProductByUid(final String codeUid);

	ProductDTO getProductByDesignation(final String designation);

	List<ProductDTO> getAllProductByCategoryId(final Long codeCategory,final  int page, final int limit);

	List<ProductDTO> getAllProductDisponibleEnPromotionAvecPrixCategory(Boolean isDisponible, Boolean isPromotion,
			Double prix, int page, int limit, Long codeCategory);

	void deleteById(final Long idProduct);

	ProductDTO updateProduct(final ProductDTO dto,final Long idProduct);

	void reduceQuantite( final Long idProduct,final  int quantity);

	 


}

package com.arcesi.ProductService.services;

import java.util.List;

import com.arcesi.ProductService.dtos.CategoryDTO;
import com.arcesi.ProductService.dtos.ProductDTO;

public interface CategoryService {
	
	 

	public ProductDTO createProduct(ProductDTO productDTO, Long categoryId);

	public CategoryDTO createCategory(final CategoryDTO dto);

	public CategoryDTO getCategoryById(final Long idCate);

	public CategoryDTO getCategoryByUid(final String uid);

	public CategoryDTO updateCategory(final CategoryDTO categoryDto,final Long idCat);

	public List<CategoryDTO> getAllCategoriesByLibelleContaining(final String partialLibelle,final int page,final  int limit);

	public void deleteCategoryById(long idCategory);

	public void deleteAllCategories();

}

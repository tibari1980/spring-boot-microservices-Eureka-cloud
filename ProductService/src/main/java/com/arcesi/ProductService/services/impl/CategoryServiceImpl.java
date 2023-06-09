package com.arcesi.ProductService.services.impl;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.ProductService.dtos.CategoryDTO;
import com.arcesi.ProductService.dtos.ProductDTO;
import com.arcesi.ProductService.entities.CategoryEntity;
import com.arcesi.ProductService.entities.ProductEntity;
import com.arcesi.ProductService.enums.ErrorsCodeEnumeration;
import com.arcesi.ProductService.exceptions.ArgumentNotValideEntityException;
import com.arcesi.ProductService.exceptions.EntityNotFoundException;
import com.arcesi.ProductService.repositories.CategoryRepository;
import com.arcesi.ProductService.repositories.ProductRepository;
import com.arcesi.ProductService.services.CategoryService;
import com.arcesi.ProductService.validators.ObjectValidators;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(isolation = Isolation.SERIALIZABLE)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ObjectValidators<ProductDTO> validator;

	@Autowired
	private ObjectValidators<CategoryDTO> validatorCat;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDTO createProduct(ProductDTO productDTO, Long categoryId) {

		log.info("Inside methode saveProduct of CategoryServiceImp  productDTO : {}  : code category : {}", productDTO,
				categoryId);
		productDTO.setCreatedAt(Instant.now());
		Map<String, String> violations = validator.validate(productDTO);
		if (!violations.isEmpty()) {

			log.error("There are errors while saving product try again ! ", violations);
			throw new ArgumentNotValideEntityException("There are errors while saving product try again !!!",
					ErrorsCodeEnumeration.PRODUCT_NOT_FOUND, violations);
		}
		CategoryEntity findCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new EntityNotFoundException(
						"Category with id : ` " + categoryId + "` not found in our data base try again !!",
						ErrorsCodeEnumeration.CATEGORY_NOT_FOUND));
		// check if Product Exist by Designation
		Optional<ProductEntity> ifExistProduct = productRepository
				.findByDesignationIgnoreCase(productDTO.getDesignation());
		if (ifExistProduct.isPresent()) {
			log.error("Product exist with : ` {} ` in our data base try again!!", productDTO.getDesignation());
			throw new EntityNotFoundException(
					"Product exist with : `" + productDTO.getDesignation() + "` in our data base try again!!",
					ErrorsCodeEnumeration.PRODUCT_NOT_FOUND);
		}

		productDTO.setCategoryDTO(modelMapper.map(findCategory, CategoryDTO.class));
		productDTO.setUidProduct(UUID.randomUUID().toString());
		ProductEntity entity = modelMapper.map(productDTO, ProductEntity.class);

		ProductEntity bean = productRepository.save(entity);
		log.info("Product created successfully !!", bean.toString());
		return modelMapper.map(bean, ProductDTO.class);

	}

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDto) {

		log.info("Inside methode createCategory of CategoryServiceImp  categoryDTO : {} ", categoryDto.toString());
		categoryDto.setCreatedAt(Instant.now());
		Map<String, String> violations = validatorCat.validate(categoryDto);
		if (!violations.isEmpty()) {

			log.error("There are errors while saving category try again ! ", violations);
			throw new ArgumentNotValideEntityException("There are errors while saving category try again !!!",
					ErrorsCodeEnumeration.PRODUCT_NOT_FOUND, violations);
		}

		// check if Category Exist by libelle
		Optional<CategoryEntity> ifExistCategory = categoryRepository.findByLibelleIgnoreCase(categoryDto.getLibelle());
		if (ifExistCategory.isPresent()) {
			log.error("Category exist with : ` {} ` in our data base try again!!", categoryDto.getLibelle());
			throw new EntityNotFoundException(
					"Category exist with : `" + categoryDto.getLibelle() + "` in our data base try again!!",
					ErrorsCodeEnumeration.CATEGORY_NOT_FOUND);
		}

		categoryDto.setUidCategory(UUID.randomUUID().toString());

		CategoryEntity cat = categoryRepository.saveAndFlush(modelMapper.map(categoryDto, CategoryEntity.class));
		log.info("Category created successfully !!", cat.toString());
		return modelMapper.map(cat, CategoryDTO.class);

	}

	@Override
	public CategoryDTO getCategoryById(Long idCate) {
		log.info("Inside getCategoryById of CategoryServiceImp  Code category : {}", idCate);
		CategoryEntity findCate = categoryRepository.findById(idCate)
				.orElseThrow(() -> new EntityNotFoundException(
						"Category with id : `" + idCate + "` not found in our data base try again",
						ErrorsCodeEnumeration.CATEGORY_NOT_FOUND));
		log.info("Category finding with id : {} , category : {} ", idCate, findCate.toString());
		return modelMapper.map(findCate, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategoryByUid(String uid) {
		log.info("Inside getCategoryByUid of CategoryServiceImp  uid category : {}", uid);
		CategoryEntity findCate = categoryRepository.uidCategory(uid)
				.orElseThrow(() -> new EntityNotFoundException(
						"Category with uid : `" + uid + "` not found in our data base try again",
						ErrorsCodeEnumeration.CATEGORY_NOT_FOUND));
		log.info("Category finding with id : {} , category : {} ", uid, findCate.toString());
		return modelMapper.map(findCate, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDto, Long idCat) {
		log.info("Inside methode updateCategory in Service CategoryServiceImp :categoryDto {}, Idcategory : {}", categoryDto ,
				idCat);
		Map<String, String> violations = validatorCat.validate(categoryDto);
		if (!violations.isEmpty()) {

			log.error("There are errors while updating category try again ! ", violations);
			throw new ArgumentNotValideEntityException("There are errors while updating category try again !!!",
					ErrorsCodeEnumeration.CATEGORY_NOT_FOUND, violations);
		}

		CategoryEntity findCategoryInOurDB = categoryRepository.findById(idCat)
				.orElseThrow(() -> new EntityNotFoundException(
						"Category with  : `" + idCat + "` not found in our data base try again",
						ErrorsCodeEnumeration.CATEGORY_NOT_FOUND));
			// check if product exist with designation
		if(!findCategoryInOurDB.getLibelle().equalsIgnoreCase(categoryDto.getLibelle())) {	
		Optional<CategoryEntity> ifExistCategory =categoryRepository
					.findByLibelleIgnoreCase(categoryDto.getLibelle());
			if (ifExistCategory.isPresent()) {
				log.error("Category exist with : ` {} ` in our data base try again!!", categoryDto.getLibelle());
				throw new EntityNotFoundException(
						"Category exist with : `" + categoryDto.getLibelle() + "` in our data base try again!!",
						ErrorsCodeEnumeration.CATEGORY_NOT_FOUND);
			}
		}

		findCategoryInOurDB.setDescription(categoryDto.getDescription());
		findCategoryInOurDB.setLibelle(categoryDto.getLibelle());
		findCategoryInOurDB.setUpdatedAt(Instant.now());
		categoryRepository.saveAndFlush(findCategoryInOurDB);
		log.info("Product updated successfully : {}", findCategoryInOurDB);
		return modelMapper.map(findCategoryInOurDB, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategoriesByLibelleContaining(String partialLibelle, int page, int limit) {
		log.info(
				"Inside methode getAllCategoriesByLibelleContaining of CategoryServiceImp  PartialLibelle : {} , page : {} , limit : {}",
				partialLibelle, page, limit);
		if (page > 0) {
			page = page + 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("codeCategory").ascending());
		Page<CategoryEntity> pageCategories = categoryRepository.findByLibelleContainingIgnoreCase(partialLibelle,
				pageable);

		List<CategoryEntity> lstCategories = pageCategories.getContent();
		List<CategoryDTO> dtosCategories = lstCategories.stream().map(cat -> modelMapper.map(cat, CategoryDTO.class))
				.collect(Collectors.toList());
		return dtosCategories;
	}

	@Override
	public void deleteCategoryById(long idCategory) {
		log.info("Inside methode deleteCategoryById of CategoryService Code category :{}", idCategory);
		CategoryEntity findCategory = categoryRepository.findById(idCategory)
				.orElseThrow(() -> new EntityNotFoundException(
						"Category with id : `" + idCategory + "` not found in our data base try again",
						ErrorsCodeEnumeration.CATEGORY_NOT_FOUND));
		if (findCategory != null) {
			log.info("Delete category id : `{}`", idCategory);
			categoryRepository.deleteById(idCategory);
		}
		log.info("Category : `{}` deleted successfully ",findCategory.toString());
	}

	
	@Override
	public void deleteAllCategories() {
		log.info("Inside methode deleteAllCategories of CategoryService Code category.");
		int ligneCategories=categoryRepository.findAll().size();
		categoryRepository.deleteAll();
		log.info("All Categories are deleted successfully ",ligneCategories);
	}
}

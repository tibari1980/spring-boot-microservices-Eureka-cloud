package com.arcesi.ProductService.services.impl;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.ProductService.dtos.ProductDTO;
import com.arcesi.ProductService.entities.ProductEntity;
import com.arcesi.ProductService.enums.ErrorsCodeEnumeration;
import com.arcesi.ProductService.exceptions.ArgumentNotValideEntityException;
import com.arcesi.ProductService.exceptions.EntityNotFoundException;
import com.arcesi.ProductService.exceptions.ProductServiceCustomException;
import com.arcesi.ProductService.repositories.ProductRepository;
import com.arcesi.ProductService.services.ProductService;
import com.arcesi.ProductService.validators.ObjectValidators;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	// @Autowired
	// private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ObjectValidators<ProductDTO> validator;

	@Override
	public List<ProductDTO> getAllProductByDesignationContaining(String partialDesignation, int page, int limit) {
		log.info(
				"Inside methode getAllProductByDesignationContaining of ProductServiceImp  PartialDesignaiton : {} , page : {} , limit : {}",
				partialDesignation, page, limit);
		if (page > 0) {
			page = page + 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("codeProduct").ascending());
		Page<ProductEntity> pageProducts = productRepository.findByDesignationContainingIgnoreCase(partialDesignation,
				pageable);

		List<ProductEntity> lstProduct = pageProducts.getContent();
		List<ProductDTO> dtosProduct = lstProduct.stream().map(pr -> modelMapper.map(pr, ProductDTO.class))
				.collect(Collectors.toList());
		return dtosProduct;
	}

	@Override
	public ProductDTO getProductById(Long idProduct) {
		log.info("Inside getProductByid of ProductServiceImp  Code Product : {}", idProduct);
		ProductEntity findProduct = productRepository.findById(idProduct)
				.orElseThrow(() -> new EntityNotFoundException(
						"Product with id : `" + idProduct + "` not found in our data base try again",
						ErrorsCodeEnumeration.PRODUCT_NOT_FOUND));
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		log.info("Product finding with id : {} , product : {} ", idProduct, findProduct.toString());
		return modelMapper.map(findProduct, ProductDTO.class);
	}

	@Override
	public ProductDTO getProductByUid(String codeUid) {
		log.info("Inside getProductByid of ProductServiceImp  Code Uid Product : {}", codeUid);
		ProductEntity findProduct = productRepository.findByUidProductIgnoreCase(codeUid)
				.orElseThrow(() -> new EntityNotFoundException(
						"Product with id : `" + codeUid + "` not found in our data base try again",
						ErrorsCodeEnumeration.PRODUCT_NOT_FOUND));
		log.info("Product finding with Uid : {} , product : {}", codeUid, findProduct.toString());
		return modelMapper.map(findProduct, ProductDTO.class);
	}

	@Override
	public ProductDTO getProductByDesignation(String designation) {
		log.info("Inside getProductByDesignation of ProductServiceImp  Code Designation Product : {}", designation);
		ProductEntity findProduct = productRepository.findByDesignationIgnoreCase(designation)
				.orElseThrow(() -> new EntityNotFoundException(
						"Product with designation : `" + designation + "` not found in our data base try again",
						ErrorsCodeEnumeration.PRODUCT_NOT_FOUND));
		// modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		log.info("Product finding with designation : {} , product : {}", designation, findProduct.toString());
		return modelMapper.map(findProduct, ProductDTO.class);
	}

	@Override
	public List<ProductDTO> getAllProductByCategoryId(Long codeCategory, int page, int limit) {
		log.info(
				"Inside methode getAllProductByCategoryId of ProductServiceImp  Code category : {} , page : {} , limit : {}",
				codeCategory, page, limit);
		if (page > 0) {
			page = page + 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("codeProduct").ascending());
		Page<ProductEntity> pageProducts = productRepository.findByCategoryEntityCodeCategory(codeCategory, pageable);

		List<ProductEntity> lstProduct = pageProducts.getContent();
		List<ProductDTO> dtosProduct = lstProduct.stream().map(pr -> modelMapper.map(pr, ProductDTO.class))
				.collect(Collectors.toList());
		return dtosProduct;
	}

	@Override
	public List<ProductDTO> getAllProductDisponibleEnPromotionAvecPrixCategory(Boolean isDisponible,
			Boolean isPromotion, Double prixUnitaire, int page, int limit, Long codeCategory) {
		log.info(
				"Inside methode getAllProductDisponibleEnPromotionAvecPrixCategory of Service ProductServiceImp  Disponible : {} , Promotion : {} , Prix Unitaire : {} , Page : {} , Limit : {} , Code Category : {}",
				isDisponible, isPromotion, prixUnitaire, page, limit, codeCategory);
		if (page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("prix").descending());
		Page<ProductEntity> pageProducts = productRepository
				.findByIsDisponibleTrueAndIsEnPromotionTrueAndPrixGreaterThanEqualAndCategoryEntityCodeCategory(
						prixUnitaire, codeCategory, pageable);
		List<ProductEntity> lstProducts = pageProducts.getContent();
		return lstProducts.stream().map(p -> modelMapper.map(p, ProductDTO.class)).collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long idProduct) {
		log.info("Inside methode deleteById of productService Code product :{}", idProduct);
		ProductEntity findProduct = productRepository.findById(idProduct)
				.orElseThrow(() -> new EntityNotFoundException(
						"Product with id : `" + idProduct + "` not found in our data base try again",
						ErrorsCodeEnumeration.PRODUCT_NOT_FOUND));
		if (findProduct != null) {
			log.info("Delete product id : `{}`", idProduct);
			productRepository.deleteById(idProduct);
		}
	}

	@Override
	public ProductDTO updateProduct(ProductDTO dto, Long idProduct) {
		log.info("Inside methode updateProduct in Service ProductServiceImp :productDTO {}, Idproduct : {}", dto,
				idProduct);
		Map<String, String> violations = validator.validate(dto);
		if (!violations.isEmpty()) {

			log.error("There are errors while saving product try again ! ", violations);
			throw new ArgumentNotValideEntityException("There are errors while saving product try again !!!",
					ErrorsCodeEnumeration.PRODUCT_NOT_FOUND, violations);
		}

		ProductEntity findProductInOurDB = productRepository.findById(idProduct)
				.orElseThrow(() -> new EntityNotFoundException(
						"Product  : " + idProduct + " not found in our data base try again",
						ErrorsCodeEnumeration.PRODUCT_NOT_FOUND));
		// check if product exist with designation
		if (!findProductInOurDB.getDesignation().equalsIgnoreCase(dto.getDesignation())) {
			Optional<ProductEntity> ifExistProduct = productRepository
					.findByDesignationIgnoreCase(dto.getDesignation());
			if (ifExistProduct.isPresent()) {
				log.error("Product exist with : ` {} ` in our data base try again!!", dto.getDesignation());
				throw new EntityNotFoundException(
						"Product exist with : `" + dto.getDesignation() + "` in our data base try again!!",
						ErrorsCodeEnumeration.PRODUCT_NOT_FOUND);
			}
		}

		findProductInOurDB.setDescription(dto.getDescription());
		findProductInOurDB.setDesignation(dto.getDesignation());
		findProductInOurDB.setPrix(dto.getPrix());
		findProductInOurDB.setQuantiteStock(dto.getQuantiteStock());
		findProductInOurDB.setIsEnPromotion(dto.getIsEnPromotion());
		findProductInOurDB.setIsDisponible(dto.getIsDisponible());
		findProductInOurDB.setUpdatedAt(Instant.now());
		productRepository.saveAndFlush(findProductInOurDB);
		log.info("Product updated successfully : {}", findProductInOurDB);
		return modelMapper.map(findProductInOurDB, ProductDTO.class);
	}

	@Override
	public void reduceQuantite(Long idProduct, int quantity) {
		log.info("Inside mehtode reduceQuantite of ProductuServiceImp  Product Id : {} , Quantite :{} ", idProduct,
				quantity);
		ProductEntity findProduct = productRepository.findById(idProduct)
				.orElseThrow(() -> new EntityNotFoundException(
						"Product with id : `" + idProduct + "` not found in our data base try again",
						ErrorsCodeEnumeration.PRODUCT_NOT_FOUND));

		if (findProduct.getQuantiteStock() < quantity) {
			throw new ProductServiceCustomException("Product does not have sufficient quantity",
					ErrorsCodeEnumeration.PRODUCT_INSUFFICIENT_QUANTITE);
		}
		findProduct.setQuantiteStock(findProduct.getQuantiteStock() - quantity);
		productRepository.saveAndFlush(findProduct);
		log.info("Product Quantity updated successfully product : {} ", findProduct.toString());
	}

}

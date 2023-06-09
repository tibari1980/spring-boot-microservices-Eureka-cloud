package com.arcesi.ProductService.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.ProductService.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


	Page<ProductEntity> findByDesignationContainingIgnoreCase(String partialDesignation, Pageable pageable);

	Optional<ProductEntity> findByUidProductIgnoreCase(final String codeUid);

	Optional<ProductEntity> findByDesignationIgnoreCase(final String designation);

	Page<ProductEntity> findByCategoryEntityCodeCategory(long parseLong, Pageable pageable);

	Page<ProductEntity> findByIsDisponibleTrueAndIsEnPromotionTrueAndPrixGreaterThanEqualAndCategoryEntityCodeCategory(
			Double prixUnitaire, Long codeCategory, Pageable pageable);

	Optional<ProductEntity> findByUidProduct(String uid);

}

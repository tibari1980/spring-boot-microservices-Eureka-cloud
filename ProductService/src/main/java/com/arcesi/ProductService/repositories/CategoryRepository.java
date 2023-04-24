package com.arcesi.ProductService.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.ProductService.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	Optional<CategoryEntity> findByLibelleIgnoreCase(final String libelle);

	Optional<CategoryEntity> uidCategory(final String uid);

	Page<CategoryEntity> findByLibelleContainingIgnoreCase(String partialLibelle, Pageable pageable);

}

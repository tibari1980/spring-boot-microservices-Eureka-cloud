package com.arcesi.ProductService.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "PRODUCTS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_PRODUCT", name = "CODE_PRODUCT_SEQUENCE"),
		@UniqueConstraint(columnNames = "UID_PRODUCT", name = "UID_PRODUCT_SEQUENCE") })

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class ProductEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "product_sequence", name = "product_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
	@Column(name = "CODE_PRODUCT", nullable = false, insertable = true, unique = true)
	private Long codeProduct;
	@Column(name = "UID_PRODUCT", insertable = true, nullable = false, updatable = false, unique = true)
	private String uidProduct;
	@Column(name = "DESINATION", insertable = true, updatable = true, unique = true, nullable = false)
	private String designation;
    @Lob
	@Column(name = "DESCRIPTION", insertable = true, updatable = true, nullable = false)
	private String description;
	@Column(name = "PRIX", insertable = true, updatable = true, nullable = false)
	private double prix;
	@Column(name = "QUANTITE", insertable = true, updatable = true, nullable = false)
	private int quantiteStock;
	@Column(name = "IS_DISPONIBLE", insertable = true, updatable = true, nullable = false)
	private Boolean isDisponible;
	@Column(name = "IS_EN_PROMOTION", insertable = true, updatable = true, nullable = false)
	private Boolean isEnPromotion;
	@Column(name = "PHOTO", insertable = true, updatable = true, nullable = false)
	private String photo;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)

	private CategoryEntity categoryEntity;
}

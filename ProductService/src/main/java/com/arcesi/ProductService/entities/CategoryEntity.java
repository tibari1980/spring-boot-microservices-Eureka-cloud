


package com.arcesi.ProductService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "CATEGORY", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_CATEGORY", name = "CODE_CATEGORY_SEQUENCE"),
		@UniqueConstraint(columnNames = "UID_CATEGORY", name = "UID_CATEGORY_SEQUENCE") })

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class CategoryEntity  extends AbstractEntity{

	
	
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1,name = "category_sequence",sequenceName = "category_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "category_sequence")
	@Column(name="CODE_CATEGORY",insertable = true,updatable = false,nullable = false)
	private Long codeCategory;
	@Column(name="UID_CATEGORY",nullable = false,insertable = true,updatable = false,length = 50,unique = true)
	private String uidCategory;
	@Column(name="LIBELLE",insertable = true,updatable = true,nullable = false,unique = true)
	private String libelle;
	@Column(name="DESCRIPTION",insertable = true,updatable = true,nullable = false)
	@Lob
	private String description;
	
	
}

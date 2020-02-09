/**
 * 
 */
package com.atanu.spring.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * This entity class is mapped with the table CATEGORY_DETAILS in database
 * 
 * @see ProductEntity
 * @see BrandEntity
 * @see ColourEntity
 * @see AvailableProductEntity
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@Entity
@Table(name = "CATEGORY_DETAILS")
public class CategoryEntity extends BaseEntity {

	private static final long serialVersionUID = -4723499017508681913L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private Long categoryId;

	@Column(name = "CATEGORY_NAME")
	private String categoryName;
}

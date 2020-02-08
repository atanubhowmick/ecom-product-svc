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
 * This entity class is mapped with the table PRODUCT_DETAILS in database
 * 
 * @see BrandEntity
 * @see CategoryEntity
 * @see ColourEntity
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@Entity
@Table(name = "PRODUCT_DETAILS")
public class ProductEntity extends BaseEntity {

	private static final long serialVersionUID = -7149032080137456750L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_DESC")
	private String productDesc;

}

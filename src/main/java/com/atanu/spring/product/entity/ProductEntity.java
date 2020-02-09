/**
 * 
 */
package com.atanu.spring.product.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * This entity class is mapped with the table PRODUCT_DETAILS in database. It
 * joins with other entity to fetch all the information.
 * 
 * @see BrandEntity
 * @see CategoryEntity
 * @see ColourEntity
 * @see AvailableProductEntity
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

	@Column(name = "PRODUCT_PRICE")
	private Double productPrice;
	
	@Column(name = "PRODUCT_SIZE")
	private String productSize;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BRAND_ID")
	private BrandEntity brandEntity;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CATEGORY_ID")
	private CategoryEntity categoryEntity;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COLOUR_ID")
	private ColourEntity colourEntity;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private AvailableProductEntity availableProductCount;
}

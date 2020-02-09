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
 * This entity class is mapped with the table COLOUR_DETAILS in database
 * 
 * @see ProductEntity
 * @see BrandEntity
 * @see CategoryEntity
 * @see AvailableProductEntity
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@Entity
@Table(name = "COLOUR_DETAILS")
public class ColourEntity extends BaseEntity {

	private static final long serialVersionUID = -8100606870046052611L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COLOUR_ID")
	private Long colourId;

	@Column(name = "COLOUR_NAME")
	private String colourName;
}

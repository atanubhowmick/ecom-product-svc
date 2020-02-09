/**
 * 
 */
package com.atanu.spring.product.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This DTO class is mapped with BrandEntity.
 *
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@ToString
public class BrandDetails implements Serializable {

	private static final long serialVersionUID = -5349647743075975089L;

	private Long brandId;
	private String brandName;
	private String brandDesc;
}

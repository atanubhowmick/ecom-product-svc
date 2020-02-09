/**
 * 
 */
package com.atanu.spring.product.constant;

import com.atanu.spring.product.entity.ProductEntity;

/**
 * This enum contains all the search key and their respective column name from
 * {@link ProductEntity}
 * 
 * @author Atanu Bhowmick
 *
 */
public enum QuerySearchEnum {
	ID("productId"),
	NAME("productName"),
	DESCRIPTION("productDesc"),
	PRICE("productPrice"),
	BRAND_NAME("brandEntity.brandName"),
	CATEGORY("categoryEntity.categoryName"),
	COLOUR("colourEntity.colourName");

	private String column;

	private QuerySearchEnum(String column) {
		this.column = column;
	}

	public String getColumn() {
		return column;
	}
}

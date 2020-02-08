/**
 * 
 */
package com.atanu.spring.product.constant;

/**
 * @author Atanu Bhowmick
 *
 */
public enum QueryFilterEnum {
	PRODUCT_NAME("productName");

	private String column;

	private QueryFilterEnum(String column) {
		this.column = column;
	}

	public String getColumn() {
		return column;
	}
}

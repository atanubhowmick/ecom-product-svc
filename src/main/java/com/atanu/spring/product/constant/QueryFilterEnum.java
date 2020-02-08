/**
 * 
 */
package com.atanu.spring.product.constant;

/**
 * @author Atanu Bhowmick
 *
 */
public enum QueryFilterEnum {
	BRAND("brandId");

	private String column;

	private QueryFilterEnum(String column) {
		this.column = column;
	}

	public String getColumn() {
		return column;
	}
}

/**
 * 
 */
package com.atanu.spring.product.constant;

/**
 * @author Atanu Bhowmick
 *
 */
public enum QuerySearchEnum {

	BRAND("brandId");

	private String column;

	private QuerySearchEnum(String column) {
		this.column = column;
	}

	public String getColumn() {
		return column;
	}
}

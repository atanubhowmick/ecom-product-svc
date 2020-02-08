/**
 * 
 */
package com.atanu.spring.product.constant;

/**
 * @author Atanu Bhowmick
 *
 */
public enum ActiveStatusEnum {

	ACTIVE('Y'), 
	INACTIVE('N');

	private Character value;

	private ActiveStatusEnum(Character value) {
		this.value = value;
	}

	public Character getValue() {
		return value;
	}
}

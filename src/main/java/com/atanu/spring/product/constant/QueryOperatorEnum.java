/**
 * 
 */
package com.atanu.spring.product.constant;

import com.atanu.spring.product.repository.QueryPageableSpecification;

/**
 * This enum contains all the operator to perform the Filter and Search operation.
 * 
 * @see QueryPageableSpecification
 * 
 * @author Atanu Bhowmick
 *
 */
public enum QueryOperatorEnum {
	IS_NULL,
	IS_NOT_NULL,
	EQUALS,
	NOT_EQUALS,
	IN;
}

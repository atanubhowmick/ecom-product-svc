/**
 * 
 */
package deb.atanu.ecom.product.constant;

import deb.atanu.ecom.product.repository.QueryPageableSpecification;

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
	EQUAL,
	NOT_EQUAL,
	LIKE,
	NOT_LIKE,
	IN,
	GREATER_THAN,
	LESS_THAN,
	GREATER_THAN_EQUAL,
	LESS_THAN_EQUAL;
}

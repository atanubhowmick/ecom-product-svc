/**
 * 
 */
package dev.atanu.ecom.product.constant;

/**
 * @author Atanu Bhowmick
 *
 */
public enum ErrorCode {

	PRODUCT_E001("No Product found"), 
	PRODUCT_E002("No Search result found"),
	PRODUCT_E003("Invalid Request. Page cannot be less than 0."),
	PRODUCT_E004("Invalid Request. Size cannot be less than 1."),
	PRODUCT_E005("Invalid Request. All fields are mandatory inside Filter."),
	PRODUCT_E006("Invalid Request. All fields are mandatory inside Search."),
	PRODUCT_E007("Invalid Json"),
	PRODUCT_E008("Invalid Request"),
	
	PRODUCT_E500("Internal Server Error. Please try again later!");
	
	private String errorMsg;

	private ErrorCode(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}

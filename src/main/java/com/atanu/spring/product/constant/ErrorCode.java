/**
 * 
 */
package com.atanu.spring.product.constant;

/**
 * @author Atanu Bhowmick
 *
 */
public enum ErrorCode {

	PE001("No Product found"), 
	PE002("No Search result found"),
	PE003("Invalid Request. Page cannot be less than 1."),
	PE004("Invalid Request. Size cannot be less than 1."),
	PE005("Invalid Request. Some fields are missing.");

	private String errorMsg;

	private ErrorCode(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}

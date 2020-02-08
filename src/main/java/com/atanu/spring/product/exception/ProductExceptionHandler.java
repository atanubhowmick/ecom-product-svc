/**
 * 
 */
package com.atanu.spring.product.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.atanu.spring.product.dto.ErrorResponse;
import com.atanu.spring.product.dto.GenericResponse;

/**
 * @author Atanu Bhowmick
 *
 */
@ControllerAdvice
public class ProductExceptionHandler {

	private static final String UNEXPECTED_ERROR_CODE = "EU001";
	private static final String UNEXPECTED_ERROR_MSG = "Internal Server Error. Please try again later!";

	private static final Logger logger = LoggerFactory.getLogger(ProductExceptionHandler.class);

	@ExceptionHandler(ProductException.class)
	public ResponseEntity<GenericResponse<?>> handleProductException(ProductException ex) {
		logger.error("Handling Product Exception... ", ex);
		ErrorResponse error = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage(), ex.getHttpStatus());
		GenericResponse<?> response = new GenericResponse<>(false, null, error);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GenericResponse<?>> handleException(Exception ex) {
		logger.error("Handling Exception... ", ex);
		ErrorResponse error = new ErrorResponse(UNEXPECTED_ERROR_CODE, UNEXPECTED_ERROR_MSG,
				HttpStatus.INTERNAL_SERVER_ERROR);
		GenericResponse<?> response = new GenericResponse<>(false, null, error);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

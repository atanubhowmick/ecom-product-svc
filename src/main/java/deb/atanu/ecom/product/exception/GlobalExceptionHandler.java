/**
 * 
 */
package deb.atanu.ecom.product.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import deb.atanu.ecom.product.dto.ErrorResponse;
import deb.atanu.ecom.product.dto.GenericResponse;

/**
 * @author Atanu Bhowmick
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final String UNEXPECTED_ERROR_CODE = "PE500";
	private static final String UNEXPECTED_ERROR_MSG = "Internal Server Error. Please try again later!";

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Method to handle {@link ProductException} and return error response
	 * 
	 * @param ex
	 * @return ResponseEntity
	 */
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<GenericResponse<?>> handleProductException(ProductException ex) {
		logger.error("Handling Product Exception... ", ex);
		ErrorResponse error = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage(), ex.getHttpStatus());
		GenericResponse<?> response = new GenericResponse<>();
		response.setError(error);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Method to handle {@link Exception} and return error response
	 * 
	 * @param ex
	 * @return ResponseEntity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<GenericResponse<?>> handleException(Exception ex) {
		logger.error("Handling Exception... ", ex);
		ErrorResponse error = new ErrorResponse(UNEXPECTED_ERROR_CODE, UNEXPECTED_ERROR_MSG,
				HttpStatus.INTERNAL_SERVER_ERROR);
		GenericResponse<?> response = new GenericResponse<>();
		response.setError(error);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

/**
 * 
 */
package dev.atanu.ecom.product.exception;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.atanu.ecom.product.constant.ErrorCode;
import dev.atanu.ecom.product.dto.ErrorResponse;
import dev.atanu.ecom.product.dto.GenericResponse;

/**
 * @author Atanu Bhowmick
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

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
	 * Method to handle {@link MethodArgumentNotValidException} and return error
	 * response
	 * 
	 * @param ex
	 * @return ResponseEntity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<GenericResponse<?>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		logger.error("Handling MethodArgumentNotValidException... ", ex);
		FieldError fieldError = ex.getBindingResult().getFieldError();
		ErrorResponse error = new ErrorResponse(ErrorCode.PRODUCT_E008.name(), fieldError.getDefaultMessage(),
				HttpStatus.BAD_REQUEST);
		GenericResponse<?> response = new GenericResponse<>();
		response.setError(error);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Method to handle {@link ConstraintViolationException} and return error
	 * response
	 * 
	 * @param ex
	 * @return ResponseEntity
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<GenericResponse<?>> handleConstraintViolationException(ConstraintViolationException ex) {
		logger.error("Handling MethodArgumentNotValidException... ", ex);
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		Optional<ConstraintViolation<?>> violation = violations.stream().findFirst();
		ErrorResponse error = null;
		if (violation.isPresent()) {
			error = new ErrorResponse(ErrorCode.PRODUCT_E008.name(), violation.get().getMessage(),
					HttpStatus.BAD_REQUEST);
		} else {
			error = new ErrorResponse(ErrorCode.PRODUCT_E008.name(), ErrorCode.PRODUCT_E008.getErrorMsg(),
					HttpStatus.BAD_REQUEST);
		}

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
		ErrorResponse error = new ErrorResponse(ErrorCode.PRODUCT_E500.name(), ErrorCode.PRODUCT_E500.getErrorMsg(),
				HttpStatus.INTERNAL_SERVER_ERROR);
		GenericResponse<?> response = new GenericResponse<>();
		response.setError(error);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

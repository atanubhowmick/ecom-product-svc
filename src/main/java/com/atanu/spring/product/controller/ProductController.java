/**
 * 
 */
package com.atanu.spring.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atanu.spring.product.dto.GenericResponse;
import com.atanu.spring.product.dto.ProductDTO;

/**
 * @author Atanu Bhowmick
 *
 */
@RestController
@RequestMapping("/api")
public class ProductController {

	@RequestMapping(value = "/product/{product-id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<GenericResponse<ProductDTO>> getProductById(@PathVariable("product-id") Long productId) {
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}

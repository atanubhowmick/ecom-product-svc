/**
 * 
 */
package com.atanu.spring.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atanu.spring.product.dto.GenericResponse;
import com.atanu.spring.product.dto.ProductDetails;
import com.atanu.spring.product.dto.QueryPageable;
import com.atanu.spring.product.service.SearchService;

/**
 * @author Atanu Bhowmick
 *
 */
@RestController
@RequestMapping("/api/product/")
public class ProductController {

	@Autowired
	private SearchService<ProductDetails, Long> productService;

	@GetMapping(value = "/get-by-id/{product-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<ProductDetails>> getProductById(@PathVariable("product-id") Long productId) {
		ProductDetails product = productService.get(productId);
		GenericResponse<ProductDetails> response = new GenericResponse<>(product);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<List<ProductDetails>>> getAllProducts() {
		List<ProductDetails> products = productService.getAll();
		GenericResponse<List<ProductDetails>> response = new GenericResponse<>(products);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<Page<ProductDetails>>> searchBySpecification(
			@RequestBody QueryPageable queryPageable) {
		Page<ProductDetails> products = productService.search(queryPageable);
		GenericResponse<Page<ProductDetails>> response = new GenericResponse<>(products);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

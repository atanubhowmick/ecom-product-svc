/**
 * 
 */
package dev.atanu.ecom.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.atanu.ecom.product.constant.DeleteTypeEnum;
import dev.atanu.ecom.product.dto.GenericResponse;
import dev.atanu.ecom.product.dto.ProductDetails;
import dev.atanu.ecom.product.dto.QueryPageable;
import dev.atanu.ecom.product.service.SearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Atanu Bhowmick
 *
 */
@Validated
@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private SearchService<ProductDetails, Long, DeleteTypeEnum> productService;

	@ApiOperation(value = "Get Product by Id", response = GenericResponse.class)
	@GetMapping(value = "/get-by-id/{product-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<ProductDetails>> getProductById(
			@ApiParam(value = "Provide Product Id to get Product Details", required = true) 
			@PathVariable("product-id") Long productId) {
		ProductDetails product = productService.get(productId);
		GenericResponse<ProductDetails> response = new GenericResponse<>(product);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Get All Products", response = GenericResponse.class)
	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<List<ProductDetails>>> getAllProducts() {
		List<ProductDetails> products = productService.getAll();
		GenericResponse<List<ProductDetails>> response = new GenericResponse<>(products);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Search and Filter Product", response = GenericResponse.class)
	@PostMapping(value = "/view", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<?>> productsBySpecification(
			@ApiParam(value = "Indicator if list is required", required = false) 
			@RequestParam(value = "isListRequired", required = false) boolean isListRequired,
			@ApiParam(value = "Provide QueryPageable with Filters/Searches", required = true) 
			@RequestBody QueryPageable queryPageable) {
		Page<ProductDetails> products = productService.search(queryPageable);
		GenericResponse<?> response = null;
		if (isListRequired) {
			// Return list as feign client is not able to consume page
			response = new GenericResponse<>(products.getContent());
		} else {
			response = new GenericResponse<>(products);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Create Product", response = GenericResponse.class)
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<ProductDetails>> createProduct(
			@ApiParam(value = "Product Details", required = true) 
			@Valid @RequestBody ProductDetails product) {
		ProductDetails productDetails = productService.create(product);
		GenericResponse<ProductDetails> response = new GenericResponse<>(productDetails);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete Product by Id", response = GenericResponse.class)
	@DeleteMapping(value = "/delete/{product-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<Boolean>> deleteProduct(
			@ApiParam(value = "Product Id", required = true)
			@PathVariable("product-id") Long productId,
			@ApiParam(value = "Delete Type", required = true)
			@RequestParam("delete-type") DeleteTypeEnum deleteType) {
		boolean isDeleted = productService.delete(productId, deleteType);
		GenericResponse<Boolean> response = new GenericResponse<>(isDeleted);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

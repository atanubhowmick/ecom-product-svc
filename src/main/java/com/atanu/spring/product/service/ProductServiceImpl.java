/**
 * 
 */
package com.atanu.spring.product.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.atanu.spring.product.dto.ProductDTO;
import com.atanu.spring.product.dto.QueryPageable;
import com.atanu.spring.product.entity.ProductEntity;

/**
 * @author Atanu Bhowmick
 *
 */
public class ProductServiceImpl implements ProductService<ProductDTO, Long> {

	@Override
	public ProductDTO get(Long id) {
		return null;
	}

	@Override
	public Page<ProductDTO> get(QueryPageable pageable) {
		return null;
	}

	@Override
	public List<ProductDTO> getAll() {
		return null;
	}

	private ProductDTO getProductDTO(ProductEntity entity) {
		ProductDTO product = new ProductDTO();
		return product;
	}
}

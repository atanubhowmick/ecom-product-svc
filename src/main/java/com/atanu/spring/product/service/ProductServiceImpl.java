/**
 * 
 */
package com.atanu.spring.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.atanu.spring.product.constant.StatusEnum;
import com.atanu.spring.product.dto.ProductDTO;
import com.atanu.spring.product.dto.QueryPageable;
import com.atanu.spring.product.entity.ProductEntity;
import com.atanu.spring.product.repository.ProductRepository;
import com.atanu.spring.product.repository.QueryPageableSpecification;

/**
 * This is the implementation class of {@link ProductService}.
 * 
 * 
 * @author Atanu Bhowmick
 *
 */
public class ProductServiceImpl implements ProductService<ProductDTO, Long> {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDTO get(Long id) {
		ProductEntity entity = productRepository.findByProductIdAndActiveStatus(id, StatusEnum.ACTIVE.getValue());
		return this.getProductDTO(entity);
	}

	@Override
	public Page<ProductDTO> get(QueryPageable queryPageable) {
		QueryPageableSpecification<ProductEntity> specification = new QueryPageableSpecification<>(queryPageable,
				StatusEnum.ACTIVE);
		Page<ProductEntity> page = productRepository.findAll(specification, queryPageable.pageable());
		List<ProductDTO> products = page.stream().map(e -> this.getProductDTO(e)).collect(Collectors.toList());
		return new PageImpl<>(products, queryPageable.pageable(), products.size());
	}

	@Override
	public List<ProductDTO> getAll() {
		List<ProductEntity> entities = productRepository.findByActiveStatus(StatusEnum.ACTIVE.getValue());
		return entities.stream().map(e -> this.getProductDTO(e)).collect(Collectors.toList());
	}

	/**
	 * THis method is to convert from ProductEntity to ProductDTO
	 * 
	 * @param entity
	 * @return ProductDTO
	 */
	private ProductDTO getProductDTO(ProductEntity entity) {
		ProductDTO product = new ProductDTO();
		product.setProductId(entity.getProductId());
		product.setProductName(entity.getProductName());
		product.setProductDesc(entity.getProductDesc());
		return product;
	}
}

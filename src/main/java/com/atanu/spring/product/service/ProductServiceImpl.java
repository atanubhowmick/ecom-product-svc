/**
 * 
 */
package com.atanu.spring.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.atanu.spring.product.constant.ErrorCode;
import com.atanu.spring.product.constant.StatusEnum;
import com.atanu.spring.product.dto.AvailableProductDetails;
import com.atanu.spring.product.dto.BrandDetails;
import com.atanu.spring.product.dto.CategoryDetails;
import com.atanu.spring.product.dto.ColourDetails;
import com.atanu.spring.product.dto.ProductDetails;
import com.atanu.spring.product.dto.QueryPageable;
import com.atanu.spring.product.entity.AvailableProductEntity;
import com.atanu.spring.product.entity.BrandEntity;
import com.atanu.spring.product.entity.CategoryEntity;
import com.atanu.spring.product.entity.ColourEntity;
import com.atanu.spring.product.entity.ProductEntity;
import com.atanu.spring.product.exception.ProductException;
import com.atanu.spring.product.repository.ProductRepository;
import com.atanu.spring.product.repository.QueryPageableSpecification;

/**
 * This is the implementation class of {@link BaseService}.
 * 
 * 
 * @author Atanu Bhowmick
 *
 */
@Service
public class ProductServiceImpl implements SearchService<ProductDetails, Long> {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDetails get(Long id) {
		ProductEntity entity = productRepository.findByProductIdAndActiveStatus(id, StatusEnum.ACTIVE.getValue());
		if (null == entity) {
			throw new ProductException(ErrorCode.PE001.name(), ErrorCode.PE001.getErrorMsg(), HttpStatus.NOT_FOUND);
		}
		return this.getProductDetails(entity);
	}

	@Override
	public List<ProductDetails> getAll() {
		List<ProductEntity> entities = productRepository.findByActiveStatus(StatusEnum.ACTIVE.getValue());
		if (CollectionUtils.isEmpty(entities)) {
			throw new ProductException(ErrorCode.PE001.name(), ErrorCode.PE001.getErrorMsg(), HttpStatus.NOT_FOUND);
		}
		return entities.stream().map(e -> this.getProductDetails(e)).collect(Collectors.toList());
	}

	@Override
	public Page<ProductDetails> search(QueryPageable queryPageable) {
		this.validate(queryPageable);
		QueryPageableSpecification<ProductEntity> specification = new QueryPageableSpecification<>(queryPageable,
				StatusEnum.ACTIVE);
		Page<ProductEntity> page = productRepository.findAll(specification, queryPageable.pageable());
		if (page.isEmpty()) {
			throw new ProductException(ErrorCode.PE002.name(), ErrorCode.PE002.getErrorMsg());
		}
		List<ProductDetails> products = page.stream().map(e -> this.getProductDetails(e)).collect(Collectors.toList());
		return new PageImpl<>(products, queryPageable.pageable(), products.size());
	}

	/**
	 * This method is to convert from ProductEntity to ProductDTO
	 * 
	 * @param entity
	 * @return ProductDTO
	 */
	private ProductDetails getProductDetails(ProductEntity entity) {
		ProductDetails product = new ProductDetails();
		product.setProductId(entity.getProductId());
		product.setProductName(entity.getProductName());
		product.setProductDesc(entity.getProductDesc());
		product.setProductPrice(entity.getProductPrice());
		product.setProductSize(entity.getProductSize());
		if (null != entity.getBrandEntity()) {
			BrandEntity brand = entity.getBrandEntity();
			BrandDetails brandDetails = new BrandDetails(brand.getBrandId(), brand.getBrandName(),
					brand.getBrandDesc());
			product.setBrandDetails(brandDetails);
		}
		if (null != entity.getCategoryEntity()) {
			CategoryEntity category = entity.getCategoryEntity();
			CategoryDetails categoryDetails = new CategoryDetails(category.getCategoryId(), category.getCategoryName());
			product.setCategoryDetails(categoryDetails);
		}
		if (null != entity.getColourEntity()) {
			ColourEntity colour = entity.getColourEntity();
			ColourDetails categoryDetails = new ColourDetails(colour.getColourId(), colour.getColourName());
			product.setColourDetails(categoryDetails);
		}
		if (null != entity.getAvailableProductCount()) {
			AvailableProductEntity availableProductEntity = entity.getAvailableProductCount();
			AvailableProductDetails availableProductDetails = new AvailableProductDetails(
					availableProductEntity.getProductId(), availableProductEntity.getProductCount());
			product.setAvailableProductDetails(availableProductDetails);
		}
		return product;
	}
}

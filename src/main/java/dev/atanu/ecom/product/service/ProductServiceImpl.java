/**
 * 
 */
package dev.atanu.ecom.product.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import dev.atanu.ecom.product.annotation.LogMethodCall;
import dev.atanu.ecom.product.constant.DeleteTypeEnum;
import dev.atanu.ecom.product.constant.ErrorCode;
import dev.atanu.ecom.product.constant.ProductConstant;
import dev.atanu.ecom.product.constant.StatusEnum;
import dev.atanu.ecom.product.dto.BrandDetails;
import dev.atanu.ecom.product.dto.CategoryDetails;
import dev.atanu.ecom.product.dto.ColourDetails;
import dev.atanu.ecom.product.dto.ProductDetails;
import dev.atanu.ecom.product.dto.QueryPageable;
import dev.atanu.ecom.product.entity.AvailableProductEntity;
import dev.atanu.ecom.product.entity.BrandEntity;
import dev.atanu.ecom.product.entity.CategoryEntity;
import dev.atanu.ecom.product.entity.ColourEntity;
import dev.atanu.ecom.product.entity.ProductEntity;
import dev.atanu.ecom.product.exception.ProductException;
import dev.atanu.ecom.product.repository.BrandRepository;
import dev.atanu.ecom.product.repository.CategoryRepository;
import dev.atanu.ecom.product.repository.ColourRepository;
import dev.atanu.ecom.product.repository.ProductRepository;
import dev.atanu.ecom.product.repository.QueryPageableSpecification;
import dev.atanu.ecom.product.util.ProductUtil;

/**
 * This is the implementation class of {@link SearchService}.
 * 
 * 
 * @author Atanu Bhowmick
 *
 */
@LogMethodCall(showParams = true, showResult = true)
@Service
public class ProductServiceImpl implements SearchService<ProductDetails, Long, DeleteTypeEnum> {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ColourRepository colourRepository;

	@Autowired
	private HazelcastInstance hazelcastInstance;

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public ProductDetails get(Long id) {
		logger.debug("Received Id: {}", id);
		ProductEntity entity = productRepository.findByProductIdAndActiveStatus(id, StatusEnum.ACTIVE.getValue());
		logger.debug("Product from DB: {}", entity);
		if (null == entity) {
			throw new ProductException(ErrorCode.PRODUCT_E001.name(), ErrorCode.PRODUCT_E001.getErrorMsg(),
					HttpStatus.NOT_FOUND);
		}
		return this.getProductDetails(entity);
	}

	@Override
	public List<ProductDetails> getAll() {
		List<ProductEntity> entities = productRepository.findByActiveStatus(StatusEnum.ACTIVE.getValue());
		if (CollectionUtils.isEmpty(entities)) {
			throw new ProductException(ErrorCode.PRODUCT_E001.name(), ErrorCode.PRODUCT_E001.getErrorMsg(),
					HttpStatus.NOT_FOUND);
		}
		return entities.stream().map(e -> this.getProductDetails(e)).collect(Collectors.toList());
	}

	@Override
	public Page<ProductDetails> search(QueryPageable queryPageable) {
		logger.debug("Received QueryPageable: {}", queryPageable);
		this.validate(queryPageable);

		IMap<String, Page<ProductDetails>> productCacheMap = hazelcastInstance
				.getMap(ProductConstant.PRODUCT_SEARCH_CACHE_MAP_KEY);
		String queryString = ProductUtil.toJson(queryPageable);
		logger.debug("Query String : {}", queryString);
		if (!StringUtils.isEmpty(queryString) && productCacheMap.containsKey(queryString)) {
			logger.debug("Search result found in Product cache. Retrieveing from the cache..");
			return productCacheMap.get(queryString);
		}
		logger.debug("Searching for the result in database..");
		QueryPageableSpecification<ProductEntity> specification = new QueryPageableSpecification<>(queryPageable,
				StatusEnum.ACTIVE);
		Page<ProductEntity> page = productRepository.findAll(specification, queryPageable.getPageable());
		if (page.isEmpty()) {
			throw new ProductException(ErrorCode.PRODUCT_E002.name(), ErrorCode.PRODUCT_E002.getErrorMsg(),
					HttpStatus.NOT_FOUND);
		}
		List<ProductDetails> products = page.stream().map(e -> this.getProductDetails(e)).collect(Collectors.toList());
		Page<ProductDetails> pageProduct = new PageImpl<>(products, queryPageable.getPageable(), products.size());

		// Save the search in cache for 10 minutes
		productCacheMap.lock(queryString);
		productCacheMap.put(queryString, pageProduct, ProductConstant.PRODUCT_CACHE_TTL, TimeUnit.MINUTES);
		productCacheMap.unlock(queryString);
		return pageProduct;
	}

	@Override
	public ProductDetails create(ProductDetails product) {
		ProductEntity entity = productRepository.save(this.getProductEntity(product));
		return this.getProductDetails(entity);
	}

	@Override
	public List<ProductDetails> create(List<ProductDetails> ts) {
		return null;
	}

	@Override
	public boolean delete(Long id, DeleteTypeEnum d) {
		if (DeleteTypeEnum.SOFT.equals(d)) {
			ProductEntity entity = productRepository.findByProductIdAndActiveStatus(id, StatusEnum.ACTIVE.getValue());
			if (Objects.isNull(entity)) {
				throw new ProductException(ErrorCode.PRODUCT_E001.name(), ErrorCode.PRODUCT_E001.getErrorMsg(),
						HttpStatus.NOT_FOUND);
			}
			entity.setActiveStatus(StatusEnum.INACTIVE.getValue());
			productRepository.save(entity);
		} else {
			Optional<ProductEntity> optionalEntity = productRepository.findById(id);
			if (!optionalEntity.isPresent()) {
				throw new ProductException(ErrorCode.PRODUCT_E001.name(), ErrorCode.PRODUCT_E001.getErrorMsg(),
						HttpStatus.NOT_FOUND);
			}
			productRepository.delete(optionalEntity.get());
		}
		return true;
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
			product.setAvailableProductCount(availableProductEntity.getProductCount());
		}
		return product;
	}

	/**
	 * Convert ProductDetails to ProductEntity
	 * 
	 * @param product
	 * @return ProductEntity
	 */
	private ProductEntity getProductEntity(ProductDetails product) {
		ProductEntity entity = new ProductEntity();
		entity.setProductName(product.getProductName());
		entity.setProductDesc(product.getProductDesc());
		entity.setProductSize(product.getProductSize());
		entity.setProductPrice(product.getProductPrice());

		// Save Brand Details
		entity.setBrandEntity(this.getBrandEntity(product.getBrandDetails()));

		// Save Category Details
		entity.setCategoryEntity(this.getCategoryEntity(product.getCategoryDetails()));

		// Save Colour Details
		entity.setColourEntity(this.getColourEntity(product.getColourDetails()));

		// Save available product details
		AvailableProductEntity availableProductEntity = new AvailableProductEntity(product.getProductId(),
				product.getAvailableProductCount());
		availableProductEntity.setActiveStatus(StatusEnum.ACTIVE.getValue());
		entity.setAvailableProductCount(availableProductEntity);

		entity.setActiveStatus(StatusEnum.ACTIVE.getValue());

		// Update entity if already exist
		if (null != product.getProductId()) {
			ProductEntity savedEntity = productRepository.findByProductIdAndActiveStatus(product.getProductId(),
					StatusEnum.ACTIVE.getValue());
			if (null != savedEntity) {
				entity.setProductId(savedEntity.getProductId());
				entity.setVersion(savedEntity.getVersion());
				AvailableProductEntity availableProduct = savedEntity.getAvailableProductCount();
				availableProduct
						.setProductCount(availableProduct.getProductCount() + product.getAvailableProductCount());
				entity.setAvailableProductCount(availableProduct);
			}
		}

		return entity;
	}

	/**
	 * 
	 * @param brand
	 * @return BrandEntity
	 */
	private BrandEntity getBrandEntity(BrandDetails brand) {
		BrandEntity brandEntity = null;
		if (null != brand.getBrandId()) {
			brandEntity = brandRepository.findByBrandIdAndActiveStatus(brand.getBrandId(),
					StatusEnum.ACTIVE.getValue());
			if(null != brandEntity) {
				brandEntity.setBrandName(brand.getBrandName());
				brandEntity.setBrandDesc(brand.getBrandDesc());
			}
		}
		if(null == brandEntity) {
			brandEntity = new BrandEntity(brand.getBrandId(), brand.getBrandName(), brand.getBrandDesc());
			brandEntity.setActiveStatus(StatusEnum.ACTIVE.getValue());
		}
		return brandEntity;
	}

	/**
	 * 
	 * @param category
	 * @return CategoryEntity
	 */
	private CategoryEntity getCategoryEntity(CategoryDetails category) {
		CategoryEntity categoryEntity = null;
		if (null != category.getCategoryId()) {
			categoryEntity = categoryRepository.findByCategoryIdAndActiveStatus(category.getCategoryId(),
					StatusEnum.ACTIVE.getValue());
			if(null != categoryEntity) {
				categoryEntity.setCategoryName(category.getCategoryName());
			}
		}
		if(null == categoryEntity) {
			categoryEntity = new CategoryEntity(category.getCategoryId(), category.getCategoryName());
			categoryEntity.setActiveStatus(StatusEnum.ACTIVE.getValue());
		}
		return categoryEntity;
	}

	/**
	 * 
	 * @param colour
	 * @return ColourEntity
	 */
	private ColourEntity getColourEntity(ColourDetails colour) {
		ColourEntity colourEntity = null;
		if (null != colour.getColourId()) {
			colourEntity = colourRepository.findByColourIdAndActiveStatus(colour.getColourId(),
					StatusEnum.ACTIVE.getValue());
			if(null != colourEntity) {
				colourEntity.setColourName(colour.getColourName());
			}
		}
		if(null == colourEntity) {
			colourEntity = new ColourEntity(colour.getColourId(), colour.getColourName());
			colourEntity.setActiveStatus(StatusEnum.ACTIVE.getValue());
		}
		return colourEntity;
	}
}

/**
 * 
 */
package com.atanu.spring.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.atanu.spring.product.constant.StatusEnum;
import com.atanu.spring.product.entity.ProductEntity;

/**
 * @author Atanu Bhowmick
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

	/**
	 * Find all products with active status (E.g Y/N)
	 * 
	 * @see StatusEnum
	 * 
	 * @param activeStatus
	 * @return List of {@link ProductEntity}
	 */
	List<ProductEntity> findByActiveStatus(Character activeStatus);

	/**
	 * Find by Product Id and active status
	 * 
	 * @param productId
	 * @param activeStatus
	 * @return ProductEntity
	 */
	ProductEntity findByProductIdAndActiveStatus(Long productId, Character activeStatus);
}

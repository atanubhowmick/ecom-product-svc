/**
 * 
 */
package com.atanu.spring.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atanu.spring.product.entity.ProductEntity;

/**
 * @author Atanu Bhowmick
 *
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

	List<ProductEntity> findByActiveStatus(Character activeStatus);

	ProductEntity findByProductIdAndActiveStatus(Long productId, Character activeConstant);
}

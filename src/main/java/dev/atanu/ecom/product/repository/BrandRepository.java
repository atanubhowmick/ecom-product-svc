/**
 * 
 */
package dev.atanu.ecom.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dev.atanu.ecom.product.constant.StatusEnum;
import dev.atanu.ecom.product.entity.BrandEntity;

/**
 * @author Atanu Bhowmick
 *
 */
@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long>, JpaSpecificationExecutor<BrandEntity> {

	/**
	 * Find all Brands with active status (E.g Y/N)
	 * 
	 * @see StatusEnum
	 * 
	 * @param activeStatus
	 * @return List of {@link BrandEntity}
	 */
	List<BrandEntity> findByActiveStatus(Character activeStatus);

	/**
	 * Find by Brand Id and active status
	 * 
	 * @param brandId
	 * @param activeStatus
	 * @return BrandEntity
	 */
	BrandEntity findByBrandIdAndActiveStatus(Long brandId, Character activeStatus);
}

/**
 * 
 */
package dev.atanu.ecom.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dev.atanu.ecom.product.constant.StatusEnum;
import dev.atanu.ecom.product.entity.CategoryEntity;

/**
 * @author Atanu Bhowmick
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {

	/**
	 * Find all Categories with active status (E.g Y/N)
	 * 
	 * @see StatusEnum
	 * 
	 * @param activeStatus
	 * @return List of {@link CategoryEntity}
	 */
	List<CategoryEntity> findByActiveStatus(Character activeStatus);

	/**
	 * Find by Category Id and active status
	 * 
	 * @param categoryId
	 * @param activeStatus
	 * @return CategoryEntity
	 */
	CategoryEntity findByCategoryIdAndActiveStatus(Long categoryId, Character activeStatus);
}

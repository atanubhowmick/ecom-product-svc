/**
 * 
 */
package dev.atanu.ecom.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dev.atanu.ecom.product.constant.StatusEnum;
import dev.atanu.ecom.product.entity.ColourEntity;

/**
 * @author Atanu Bhowmick
 *
 */
@Repository
public interface ColourRepository extends JpaRepository<ColourEntity, Long>, JpaSpecificationExecutor<ColourEntity> {

	/**
	 * Find all Colours with active status (E.g Y/N)
	 * 
	 * @see StatusEnum
	 * 
	 * @param activeStatus
	 * @return List of {@link ColourEntity}
	 */
	List<ColourEntity> findByActiveStatus(Character activeStatus);

	/**
	 * Find by Product Id and active status
	 * 
	 * @param colourId
	 * @param activeStatus
	 * @return ColourEntity
	 */
	ColourEntity findByColourIdAndActiveStatus(Long colourId, Character activeStatus);
}

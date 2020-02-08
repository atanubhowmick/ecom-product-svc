/**
 * 
 */
package com.atanu.spring.product.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.atanu.spring.product.dto.QueryPageable;
import com.atanu.spring.product.repository.QueryPageableSpecification;

/**
 * Interface to provide search related operations
 * 
 * @author Atanu Bhowmick
 *
 */
public interface ProductService<T, ID> {

	/**
	 * Find by Id
	 * 
	 * @param ID
	 * @return
	 */
	T get(ID id);

	
	/**
	 * This method provides Filter and Search functilities by {@link QueryPageable}
	 * and {@link QueryPageableSpecification}
	 * 
	 * Proper column name needs to be sent inside QueryPageable Filters and Searches
	 * 
	 * @see QueryPageable
	 * 
	 * @param queryPageable
	 * @return
	 */
	Page<T> get(QueryPageable queryPageable);

	
	/**
	 * Find all
	 * 
	 * @return List of T
	 */
	List<T> getAll();

}

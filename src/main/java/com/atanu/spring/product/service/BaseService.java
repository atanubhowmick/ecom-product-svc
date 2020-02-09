/**
 * 
 */
package com.atanu.spring.product.service;

import java.util.List;

/**
 * Interface to provide search related operations
 * 
 * @author Atanu Bhowmick
 *
 */
public interface BaseService<T, K> {

	/**
	 * Find by Id
	 * 
	 * @param ID
	 * @return
	 */
	T get(K id);

	/**
	 * Find all
	 * 
	 * @return List of T
	 */
	List<T> getAll();

}

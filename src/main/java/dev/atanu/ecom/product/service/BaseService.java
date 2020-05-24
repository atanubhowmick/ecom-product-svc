/**
 * 
 */
package dev.atanu.ecom.product.service;

import java.util.List;
import java.util.Map;

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

	/**
	 * Create
	 * 
	 * @param t
	 * @return
	 */
	T create(T t);

	/**
	 * Add available products
	 * 
	 * @param t
	 * @return
	 */
	Map<K, T> add(Map<K, Long> map);

	/**
	 * Delete
	 * 
	 * @param k
	 * @return
	 */
	boolean delete(K k, DeleteTypeEnum deleteType);
	
	/**
	 * Enum to represent the delete type
	 *
	 */
	public enum DeleteTypeEnum {
		SOFT,
		HARD;
	}

}

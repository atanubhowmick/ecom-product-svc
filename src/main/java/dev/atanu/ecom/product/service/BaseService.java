/**
 * 
 */
package dev.atanu.ecom.product.service;

import java.util.List;

/**
 * Interface to provide search related operations
 * 
 * @author Atanu Bhowmick
 *
 */
public interface BaseService<T, K, D extends Enum<?>> {

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
	 * Create Multiple
	 * 
	 * @param ts
	 * @return
	 */
	List<T> create(List<T> ts);

	/**
	 * Delete
	 * 
	 * @param k
	 * @return
	 */
	boolean delete(K k, D d);

}

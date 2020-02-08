/**
 * 
 */
package com.atanu.spring.product.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.atanu.spring.product.dto.QueryPageable;

/**
 * @author Atanu Bhowmick
 *
 */
public interface ProductService<T, K> {

	T get(K k);

	Page<T> get(QueryPageable pageable);

	List<T> getAll();

}

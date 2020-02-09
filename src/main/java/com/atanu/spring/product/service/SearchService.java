/**
 * 
 */
package com.atanu.spring.product.service;

import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.atanu.spring.product.constant.ErrorCode;
import com.atanu.spring.product.dto.QueryFilter;
import com.atanu.spring.product.dto.QueryPageable;
import com.atanu.spring.product.dto.QuerySearch;
import com.atanu.spring.product.exception.ProductException;
import com.atanu.spring.product.repository.QueryPageableSpecification;

/**
 * @author Atanu Bhowmick
 *
 */
public interface SearchService<T, K> extends BaseService<T, K> {

	/**
	 * This method provides Filter and Search functionalities by
	 * {@link QueryPageable} and {@link QueryPageableSpecification}
	 * 
	 * Proper column name needs to be sent inside QueryPageable Filters and Searches
	 * 
	 * @see QueryPageable
	 * 
	 * @param queryPageable
	 * @return
	 */
	Page<T> search(QueryPageable queryPageable);

	/**
	 * Default method to validate the {@link QueryPageable}
	 * 
	 * @param queryPageable
	 */
	public default void validate(QueryPageable queryPageable) {
		if (queryPageable.getPage() == null || queryPageable.getPage() <= 0) {
			throw new ProductException(ErrorCode.PE003.name(), ErrorCode.PE003.getErrorMsg());
		}
		if (queryPageable.getSize() == null || queryPageable.getSize() <= 0) {
			throw new ProductException(ErrorCode.PE004.name(), ErrorCode.PE004.getErrorMsg());
		}

		if (!CollectionUtils.isEmpty(queryPageable.getFilters())) {
			for (QueryFilter filter : queryPageable.getFilters()) {
				if (StringUtils.isEmpty(filter.getFilterBy()) || null == filter.getFilterOperator()
						|| null == filter.getFilterValue()) {
					throw new ProductException(ErrorCode.PE005.name(), ErrorCode.PE005.getErrorMsg());
				}
				filter.setFilterColumn(filter.getFilterBy().getColumn());
			}
		}

		if (!CollectionUtils.isEmpty(queryPageable.getSearches())) {
			for (QuerySearch search : queryPageable.getSearches()) {
				if (StringUtils.isEmpty(search.getSearchBy()) || null == search.getSearchOperator()
						|| null == search.getSearchValue()) {
					throw new ProductException(ErrorCode.PE005.name(), ErrorCode.PE005.getErrorMsg());
				}
				search.setSearchColumn(search.getSearchBy().getColumn());
			}
		}
	}
}

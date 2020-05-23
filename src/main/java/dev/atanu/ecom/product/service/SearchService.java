/**
 * 
 */
package dev.atanu.ecom.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import dev.atanu.ecom.product.constant.ErrorCode;
import dev.atanu.ecom.product.dto.QueryFilter;
import dev.atanu.ecom.product.dto.QueryPageable;
import dev.atanu.ecom.product.dto.QuerySearch;
import dev.atanu.ecom.product.exception.ProductException;
import dev.atanu.ecom.product.repository.QueryPageableSpecification;

/**
 * @author Atanu Bhowmick
 *
 */
public interface SearchService<T, K, D extends Enum<?>> extends BaseService<T, K, D> {

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
		if (queryPageable.getPage() == null || queryPageable.getPage() < 0) {
			throw new ProductException(ErrorCode.PRODUCT_E003.name(), ErrorCode.PRODUCT_E003.getErrorMsg());
		}
		if (queryPageable.getSize() == null || queryPageable.getSize() <= 0) {
			throw new ProductException(ErrorCode.PRODUCT_E004.name(), ErrorCode.PRODUCT_E004.getErrorMsg());
		}
		this.validateFilters(queryPageable.getFilters());
		this.validateSearches(queryPageable.getSearches());
	}

	/**
	 * Validate Filters
	 * 
	 * @param filters
	 */
	default void validateFilters(List<QueryFilter> filters) {
		if (!CollectionUtils.isEmpty(filters)) {
			for (QueryFilter filter : filters) {
				if (StringUtils.isEmpty(filter.getFilterBy()) || null == filter.getFilterOperator()
						|| null == filter.getFilterValue()) {
					throw new ProductException(ErrorCode.PRODUCT_E005.name(), ErrorCode.PRODUCT_E005.getErrorMsg());
				}
			}
		}
	}

	/**
	 * Validate Searches
	 * 
	 * @param searches
	 */
	default void validateSearches(List<QuerySearch> searches) {
		if (!CollectionUtils.isEmpty(searches)) {
			for (QuerySearch search : searches) {
				if (StringUtils.isEmpty(search.getSearchBy()) || null == search.getSearchOperator()
						|| null == search.getSearchValue()) {
					throw new ProductException(ErrorCode.PRODUCT_E006.name(), ErrorCode.PRODUCT_E006.getErrorMsg());
				}
			}
		}
	}
}

/**
 * 
 */
package com.atanu.spring.product.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import com.atanu.spring.product.constant.StatusEnum;
import com.atanu.spring.product.dto.QueryFilter;
import com.atanu.spring.product.dto.QueryPageable;
import com.atanu.spring.product.dto.QuerySearch;

/**
 * @author Atanu Bhowmick
 *
 */
public class QueryPageableSpecification<T> implements Specification<T> {

	private static final long serialVersionUID = -42694488014989098L;

	private QueryPageable queryPageable;
	private StatusEnum activeStatus;

	public QueryPageableSpecification(QueryPageable queryPageable, StatusEnum activeStatus) {
		this.queryPageable = queryPageable;
		this.activeStatus = activeStatus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		// For Active check. Equivalent to soft delete in database.
		Predicate predicate = criteriaBuilder.equal(root.get("isActive"), activeStatus.getValue());

		// ------ Filter ------
		Predicate filterPredicate = null;
		if (null != queryPageable && !CollectionUtils.isEmpty(queryPageable.getFilters())) {
			for (QueryFilter filter : queryPageable.getFilters()) {
				String column = filter.getFilterColumn();
				Object value = filter.getFilterValue();
				switch (filter.getFilterOperator()) {
				case IS_NULL:
					filterPredicate = criteriaBuilder.isNull(root.get(column));
					break;
				case IS_NOT_NULL:
					filterPredicate = criteriaBuilder.isNotNull(root.get(column));
					break;
				case EQUALS:
					filterPredicate = criteriaBuilder.equal(root.get(column), value);
					break;
				case NOT_EQUALS:
					filterPredicate = criteriaBuilder.notEqual(root.get(column), value);
					break;
				case IN:
					if (value instanceof List && !CollectionUtils.isEmpty((List<Object>) value)) {
						filterPredicate = root.get(column).in((List<Object>) value);
					}
					break;
				}

				// Add to filter predicate
				if (null != filterPredicate) {
					// Filter is AND condition
					predicate = criteriaBuilder.and(predicate, filterPredicate);
				}
			}
		}

		// ------ Search ------
		List<Predicate> searchPredicates = null;
		if (null != queryPageable && !CollectionUtils.isEmpty(queryPageable.getSearches())) {
			searchPredicates = new ArrayList<>();
			for (QuerySearch search : queryPageable.getSearches()) {
				switch (search.getSearchOperator()) {
				case IS_NULL:
					break;
				case IN:
					searchPredicates.add(root.get(search.getSearchColumn()).in((List<Object>) search.getSearchValue()));
					break;
				default:
					searchPredicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get(search.getSearchColumn())),
							"%" + search.getSearchValue().toString().toUpperCase() + "%"));
					break;
				}
			}
		}
		if (!CollectionUtils.isEmpty(searchPredicates)) {
			// Search is OR condition
			Predicate searchPredicate = criteriaBuilder
					.or(searchPredicates.toArray(new Predicate[searchPredicates.size()]));
			predicate = criteriaBuilder.and(predicate, searchPredicate);
		}

		return predicate;
	}

}

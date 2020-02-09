/**
 * 
 */
package com.atanu.spring.product.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.atanu.spring.product.constant.QueryOrderByEnum;
import com.atanu.spring.product.constant.QueryOrderEnum;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Contains all the page information with filter and search criteria
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "QueryPageable", description = "Contains all the page information with filter and search criteria")
public class QueryPageable implements Serializable {

	private static final long serialVersionUID = -2891214167142421480L;

	private Integer page = 0;
	private Integer size = 10;
	private QueryOrderByEnum orderBy;
	private QueryOrderEnum order;
	private List<QueryFilter> filters;
	private List<QuerySearch> searches;

	public Pageable pageable() {
		Pageable pageable = null;
		if (null != orderBy) {
			if (null != order && QueryOrderEnum.desc.equals(order)) {
				pageable = PageRequest.of(page, size, Sort.by(orderBy.getColumn()).descending());
			} else {
				pageable = PageRequest.of(page, size, Sort.by(orderBy.getColumn()));
			}
		} else {
			pageable = PageRequest.of(page, size);
		}
		return pageable;
	}
}

/**
 * 
 */
package com.atanu.spring.product.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.atanu.spring.product.constant.QueryOrderEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class QueryPageable implements Serializable {

	private static final long serialVersionUID = -2891214167142421480L;

	private Integer page = 0;
	private Integer size = 10;
	private QueryOrderEnum orderBy;
	private List<QueryFilter> filters;
	private List<QuerySearch> searches;

	public Pageable pageable() {
		Pageable pageable = null;
		if (orderBy != null) {
			pageable = PageRequest.of(page, size, Sort.by(orderBy.name()));
		} else {
			pageable = PageRequest.of(page, size);
		}
		return pageable;
	}
}

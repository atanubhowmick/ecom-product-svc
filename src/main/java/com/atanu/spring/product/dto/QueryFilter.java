/**
 * 
 */
package com.atanu.spring.product.dto;

import java.io.Serializable;

import com.atanu.spring.product.constant.QueryFilterEnum;
import com.atanu.spring.product.constant.QueryOperatorEnum;

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
public class QueryFilter implements Serializable {

	private static final long serialVersionUID = -7229466529156636679L;

	private QueryFilterEnum filterBy;
	private Object filterValue;
	private QueryOperatorEnum filterOperator;
	private String filterColumn;
}

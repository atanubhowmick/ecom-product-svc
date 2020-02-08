/**
 * 
 */
package com.atanu.spring.product.dto;

import java.io.Serializable;

import com.atanu.spring.product.constant.QueryOperatorEnum;
import com.atanu.spring.product.constant.QuerySearchEnum;

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
public class QuerySearch implements Serializable {

	private static final long serialVersionUID = 3736588459667327170L;

	private QuerySearchEnum searchBy;
	private Object searchValue;
	private QueryOperatorEnum searchOperator;
	private String searchColumn;
}

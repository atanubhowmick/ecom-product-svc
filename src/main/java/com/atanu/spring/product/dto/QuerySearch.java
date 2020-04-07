/**
 * 
 */
package com.atanu.spring.product.dto;

import com.atanu.spring.product.constant.QueryOperatorEnum;
import com.atanu.spring.product.constant.QuerySearchEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuerySearch extends AbstractBaseDTO {

	private static final long serialVersionUID = 5288860808963640238L;

	private QuerySearchEnum searchBy;
	private Object searchValue;
	private QueryOperatorEnum searchOperator;
}

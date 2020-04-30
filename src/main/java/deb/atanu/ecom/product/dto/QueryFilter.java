/**
 * 
 */
package deb.atanu.ecom.product.dto;

import deb.atanu.ecom.product.constant.QueryFilterEnum;
import deb.atanu.ecom.product.constant.QueryOperatorEnum;
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
public class QueryFilter extends AbstractBaseDTO {

	private static final long serialVersionUID = 6505098951618946485L;

	private QueryFilterEnum filterBy;
	private Object filterValue;
	private QueryOperatorEnum filterOperator;
}

/**
 * 
 */
package com.atanu.spring.product.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@ApiModel(value = "GenericResponse", description = "Commom response object with payload and error response")
public class GenericResponse<T> implements Serializable {

	private static final long serialVersionUID = 1090351768369181315L;

	@ApiModelProperty(value = "Response Success/Failed indicator", example = "true")
	private boolean isSuccess;

	@ApiModelProperty(value = "Final Response Object")
	private T payload;

	@ApiModelProperty(value = "Error Response for any error scenario")
	private ErrorResponse error;

	public GenericResponse(T payload) {
		this.isSuccess = true;
		this.payload = payload;
	}
}

/**
 * 
 */
package com.atanu.spring.product.dto;

import java.io.Serializable;

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
public class GenericResponse<T> implements Serializable {

	private static final long serialVersionUID = 1090351768369181315L;

	private boolean isSuccess;
	private T payload;
	private ErrorResponse error;

	public GenericResponse(T payload) {
		this.isSuccess = true;
		this.payload = payload;
	}
}

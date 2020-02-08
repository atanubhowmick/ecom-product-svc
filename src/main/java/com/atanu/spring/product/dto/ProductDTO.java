/**
 * 
 */
package com.atanu.spring.product.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@ToString
public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1963874875184557520L;

	private Long productId;
	private String productName;
	private String productDesc;
}

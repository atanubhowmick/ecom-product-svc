/**
 * 
 */
package com.atanu.spring.product.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class contains all the information related to Product
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@ToString
@ApiModel(value = "ProductDTO", description = "Contains all the details about Product")
public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1963874875184557520L;

	@ApiModelProperty(value = "Product Id", example = "1001")
	private Long productId;

	@ApiModelProperty(value = "Product Name", example = "Redmi Note-8 Pro")
	private String productName;

	@ApiModelProperty(value = "Product Description", example = "Redmi Note-8 Pro with 16 GB RAM and 64 GB Memory")
	private String productDesc;
}

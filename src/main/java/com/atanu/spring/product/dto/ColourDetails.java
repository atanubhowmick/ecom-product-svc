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
 * This DTO class is mapped with ColourEntity
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ColourDetails implements Serializable {

	private static final long serialVersionUID = -5063664403711156410L;

	private Long colourId;
	private Long colourName;
}

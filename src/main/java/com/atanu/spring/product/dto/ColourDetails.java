/**
 * 
 */
package com.atanu.spring.product.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * This DTO class is mapped with ColourEntity
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@Entity
@Table(name = "COLOUR_DETAILS")
public class ColourDetails implements Serializable {

	private static final long serialVersionUID = -5063664403711156410L;

	private Long colourId;
	private Long colourName;
}

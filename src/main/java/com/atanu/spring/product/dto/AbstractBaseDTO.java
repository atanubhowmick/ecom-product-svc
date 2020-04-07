/**
 * 
 */
package com.atanu.spring.product.dto;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Atanu Bhowmick
 *
 */
public abstract class AbstractBaseDTO implements Serializable {
	
	private static final long serialVersionUID = 5274512655216948657L;
	
	/*
	 * Overriding toString method to return JSON String
	 *   
	 * https://stackoverflow.com/questions/16527932/ok-to-use-json-output-as-default-for-tostring
	 */
	@Override
	public String toString() {
		String objectString = null;
		try {
			objectString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			objectString = super.toString();
		}
		return objectString;
	}
}

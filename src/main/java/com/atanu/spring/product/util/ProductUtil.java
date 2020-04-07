/**
 * 
 */
package com.atanu.spring.product.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atanu.spring.product.constant.ProductConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Atanu Bhowmick
 *
 */
public class ProductUtil {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(ProductUtil.class);
	
	/**
	 * Default Constructor
	 */
	private ProductUtil() {
	}
	
	/**
	 * Convert object to string using jackson mapper
	 * 
	 * @param object
	 * @return
	 */
	public static String convertToString(Object object) {
		String str = ProductConstant.EMPTY_STRING;
		try {
			str= mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.debug("Error while converting object to string", e);
		}
		return str;
	}

}

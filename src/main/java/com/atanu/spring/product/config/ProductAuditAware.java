/**
 * 
 */
package com.atanu.spring.product.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import com.atanu.spring.product.constant.ProductConstant;

/**
 * @author Atanu Bhowmick
 *
 */
public class ProductAuditAware implements AuditorAware<Long>{

	@Override
	public Optional<Long> getCurrentAuditor() {
		Long userId = ProductConstant.DEFAULT_USER_ID;
		
		/*
		 * Here we can read the user information from Spring Security Context
		 * for logged-in scenario and set the userId in AuditorAware. 
		 * 
		 * This will help to monitor all insert/update query in Database tables.
		 * 
		 * CREATED_BY and LAST_MODIFIED_BY columns will be automatically updated 
		 */
		
		return Optional.of(userId);
	}

}
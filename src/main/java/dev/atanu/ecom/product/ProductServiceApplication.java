package dev.atanu.ecom.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import dev.atanu.ecom.product.dto.ProjectBuildDetails;

@EnableEurekaClient
@SpringBootApplication
public class ProductServiceApplication {

	@Value("${info.app.version}")
	private String projectVersion;
	
	@Value("${build.number}")
	private String buildNumber;

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
	
	@Bean
	public ProjectBuildDetails getBuildNumber() {
		ProjectBuildDetails details = new ProjectBuildDetails(projectVersion, buildNumber);
		logger.info("Project Build Details : {}", details);
		return details;
	}

}

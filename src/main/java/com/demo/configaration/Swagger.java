package com.demo.configaration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class Swagger {
	
	@Bean
	OpenAPI customOpenAPI() {
		
		return new OpenAPI().info(new Info().title("Case Application").
				version("1.0").description("WebClient for managing Caseworker Application"));
				
	}
	
}
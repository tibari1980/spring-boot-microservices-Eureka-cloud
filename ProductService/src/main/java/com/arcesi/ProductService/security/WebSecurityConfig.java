package com.arcesi.ProductService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {
		http
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers(
					"/api/v1/product/**",
					"/swagger-ui.html",
					"/swagger-resources/**",
					"/v3/api-docs",
					"/v3/api-docs/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
		return http.build();
	}

}
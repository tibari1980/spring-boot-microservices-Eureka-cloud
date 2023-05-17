package com.arcesi.coudgateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class OktaOAuth2WebSecurity {

	
	@Bean
	public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
		http
				.authorizeExchange()
				.anyExchange().authenticated()
				.and()
				.oauth2ResourceServer()
				.jwt();
		return http.build();
	}
}

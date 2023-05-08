package com.arcesi.coudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@SpringBootApplication
public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
		
	}

	@Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
        
        // the circuitBreakerConfig and timeLimiterConfig objects

        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
          .timeLimiterConfig(TimeLimiterConfig.ofDefaults())
          .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
          .build());
    } 

}

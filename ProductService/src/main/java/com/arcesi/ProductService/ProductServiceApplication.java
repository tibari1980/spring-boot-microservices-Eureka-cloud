package com.arcesi.ProductService;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductServiceApplication {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return mapper;
	}
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}

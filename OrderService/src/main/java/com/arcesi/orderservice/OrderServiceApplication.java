
package com.arcesi.orderservice;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.arcesi.orderservice.external.interceptor.RestTemplateInterceptor;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	@Autowired
	ClientRegistrationRepository clientRegistrationRepository;
	@Autowired
	OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return mapper;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(Arrays.asList(new RestTemplateInterceptor(
				clientManager(clientRegistrationRepository, oAuth2AuthorizedClientRepository))

		));
		return restTemplate;
	}

	@Bean
	public OAuth2AuthorizedClientManager clientManager(ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {

		OAuth2AuthorizedClientProvider auth2AuthorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
				.clientCredentials().build();
		DefaultOAuth2AuthorizedClientManager auth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
				clientRegistrationRepository, oAuth2AuthorizedClientRepository);

		auth2AuthorizedClientManager.setAuthorizedClientProvider(auth2AuthorizedClientProvider);
		return auth2AuthorizedClientManager;

	}
   
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}

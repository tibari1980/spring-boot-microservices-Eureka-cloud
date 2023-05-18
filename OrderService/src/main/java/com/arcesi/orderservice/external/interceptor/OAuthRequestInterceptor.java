package com.arcesi.orderservice.external.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class OAuthRequestInterceptor implements RequestInterceptor {

	@Autowired
	private OAuth2AuthorizedClientManager auth2AuthorizedClientManager;

	@Override
	public void apply(RequestTemplate template) {

		template.header("Authorization",
				"Bearer " +  auth2AuthorizedClientManager.authorize(OAuth2AuthorizeRequest
						.withClientRegistrationId("internal-client").principal("internal").build()).getAccessToken()
						.getTokenValue());

	}

}

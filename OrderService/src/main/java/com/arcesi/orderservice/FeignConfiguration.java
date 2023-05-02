package com.arcesi.orderservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arcesi.orderservice.external.client.decoder.RetrieveMessageErrorDecoder;

import feign.Logger;
import feign.Logger.Level;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfiguration {

	
	
	
	  @Bean 
	  ErrorDecoder errorDecoder() { 
		  return new RetrieveMessageErrorDecoder();
	   }
	  
	 Logger.Level feignLoggerLevel(){ return Level.FULL; }
}

package com.arcesi.orderservice.external.client.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionMessage {

	private String timeStramp;
	private int status;
	private String error;
	private String message;
	private String path;
	
}

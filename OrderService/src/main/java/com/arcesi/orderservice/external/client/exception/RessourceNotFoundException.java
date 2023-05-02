package com.arcesi.orderservice.external.client.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class RessourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7872230957054420044L;

	private String codeError;

	public RessourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RessourceNotFoundException(String message) {
		super(message);

	}

	public RessourceNotFoundException(String message, String codeError) {
		super(message);
		this.codeError = codeError;
	}

}

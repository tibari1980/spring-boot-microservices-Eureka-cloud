package com.arcesi.configservice.exceptions;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorEnum;
	private Map<String, String> lstErrors;

	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);

	}

	public InvalidEntityException(String message) {
		super(message);

	}

	public InvalidEntityException(String message, Throwable cause, String errosEnum) {
		super(message, cause);
		this.errorEnum = errosEnum;
	}

	public InvalidEntityException(String message, String errosEnum, Map<String, String> lstErrors) {
		super(message);
		this.errorEnum = errosEnum;
		this.lstErrors = lstErrors;
	}

	public InvalidEntityException(String message, String errosEnum) {
		super(message);
		this.errorEnum = errosEnum;
	}
}

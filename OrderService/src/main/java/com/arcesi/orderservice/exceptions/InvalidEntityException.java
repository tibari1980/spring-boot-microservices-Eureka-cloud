package com.arcesi.orderservice.exceptions;

import java.util.Map;

import com.arcesi.orderservice.enums.ErrorsCodeEnumeration;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InvalidEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorsCodeEnumeration errorEnum;
	private Map<String, String> lstErrors;

	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);

	}

	public InvalidEntityException(String message) {
		super(message);

	}

	public InvalidEntityException(String message, Throwable cause, ErrorsCodeEnumeration errosEnum) {
		super(message, cause);
		this.errorEnum = errosEnum;
	}

	public InvalidEntityException(String message, ErrorsCodeEnumeration errosEnum, Map<String, String> lstErrors) {
		super(message);
		this.errorEnum = errosEnum;
		this.lstErrors = lstErrors;
	}

	public InvalidEntityException(String message, ErrorsCodeEnumeration errosEnum) {
		super(message);
		this.errorEnum = errosEnum;
	}
}

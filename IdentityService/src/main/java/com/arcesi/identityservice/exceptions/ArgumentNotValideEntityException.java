package com.arcesi.identityservice.exceptions;

import java.util.Map;

import com.arcesi.identityservice.enums.ErrorsCodeEnumeration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArgumentNotValideEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorsCodeEnumeration errorEnum;
	private Map<String, String> lstErrors;
	
	public ArgumentNotValideEntityException(String message, Throwable cause) {
		super(message, cause);

	}

	public ArgumentNotValideEntityException(String message) {
		super(message);

	}

	public ArgumentNotValideEntityException(String message, Throwable cause, ErrorsCodeEnumeration errosEnum) {
		super(message, cause);
		this.errorEnum = errosEnum;
	}

	public ArgumentNotValideEntityException(String message, ErrorsCodeEnumeration errosEnum, Map<String, String> errors) {
		super(message);
		this.errorEnum = errosEnum;
		this.lstErrors = errors;
	}

	public ArgumentNotValideEntityException(String message, ErrorsCodeEnumeration errosEnum) {
		super(message);
		this.errorEnum = errosEnum;
	}

}

package com.arcesi.identityservice.exceptions;

import com.arcesi.identityservice.enums.ErrorsCodeEnumeration;

import lombok.Getter;


@Getter
public class ArgumentNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorsCodeEnumeration errorEnum;

	public ArgumentNotValidException(String message) {
		super(message);

	}

	public ArgumentNotValidException(String message, ErrorsCodeEnumeration errorEnum) {
		super(message);
		this.errorEnum = errorEnum;
	}

}
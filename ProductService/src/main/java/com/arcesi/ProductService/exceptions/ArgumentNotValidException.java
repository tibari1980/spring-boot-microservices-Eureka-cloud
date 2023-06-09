package com.arcesi.ProductService.exceptions;

import com.arcesi.ProductService.enums.ErrorsCodeEnumeration;

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

package com.arcesi.ProductService.exceptions;

import com.arcesi.ProductService.enums.ErrorsCodeEnumeration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductServiceCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorsCodeEnumeration errorEnum;

	public ProductServiceCustomException(String message, Throwable cause) {
		super(message, cause);

	}

	public ProductServiceCustomException(String message) {
		super(message);

	}

	public ProductServiceCustomException(String message, Throwable cause, ErrorsCodeEnumeration errosEnum) {
		super(message, cause);
		this.errorEnum = errosEnum;
	}

	public ProductServiceCustomException(String message, ErrorsCodeEnumeration errosEnum) {
		super(message);
		this.errorEnum = errosEnum;
	}
}

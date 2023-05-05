package com.arcesi.configservice.exceptions;


import com.arcesi.configservice.enums.ErrorsCodeEnumeration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7872230957054420044L;

	private ErrorsCodeEnumeration codeErrors;

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
		 
	}

	public EntityNotFoundException(String message) {
		super(message);

	}

	public EntityNotFoundException(String message, ErrorsCodeEnumeration codeErrors) {
		super(message);
		this.codeErrors = codeErrors;
	}

}

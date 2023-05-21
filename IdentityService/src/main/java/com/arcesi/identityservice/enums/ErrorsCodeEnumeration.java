package com.arcesi.identityservice.enums;

public enum ErrorsCodeEnumeration {

	USER_NOT_FOUND(1000), 
	USER_NOT_VALIDE(1001), 
	
	ROLE_NOT_FOUND(2000), 
	ROLE_NOT_VALIDE(2001),
	
	PARAMETRE_NOT_VALID(3000);
	
	

	public final int code;

	private ErrorsCodeEnumeration(final int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}

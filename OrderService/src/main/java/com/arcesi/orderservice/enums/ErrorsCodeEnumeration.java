package com.arcesi.orderservice.enums;

public enum ErrorsCodeEnumeration {

	CLIENT_NOT_FOUND(1000), 
	CLIENT_NOT_VALIDE(1001), 
	
	ORDER_NOT_FOUND(2000), 
	ORDER_NOT_VALIDE(2001),
	
	PARAMETRE_NOT_VALID(3000);
	
	

	public final int code;

	private ErrorsCodeEnumeration(final int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}

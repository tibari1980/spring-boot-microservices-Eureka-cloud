package com.arcesi.configservice.enums;

public enum ErrorsCodeEnumeration {

	TRANSACTIONDETAILS_NOT_FOUND(1000), 
	TRANSACTIONDETAILS_NOT_VALIDE(1001); 
	

	public final int code;

	private ErrorsCodeEnumeration(final int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}

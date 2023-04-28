package com.arcesi.orderservice.enums;

public enum EtatOrder {
	
	CREATED("CREATED"),
	VALIDEE("VALIDEE");
	
	final String code;
	
	private EtatOrder(final String code) {
		this.code=code;
	}
	
	public String getCode() {
		return code;
	}

}

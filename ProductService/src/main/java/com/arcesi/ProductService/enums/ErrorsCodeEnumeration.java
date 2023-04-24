package com.arcesi.ProductService.enums;

public enum ErrorsCodeEnumeration {

	CATEGORY_NOT_FOUND(1000), 
	CATEGORY_NOT_VALIDE(1001), 
	
	PRODUCT_NOT_FOUND(2000), 
	PRODUCT_NOT_VALIDE(2001),
	PARAMETRE_NOT_VALID(3000);
	
	

	public final int code;

	private ErrorsCodeEnumeration(final int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}

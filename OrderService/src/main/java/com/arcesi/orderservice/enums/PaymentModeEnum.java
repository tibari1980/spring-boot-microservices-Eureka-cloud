package com.arcesi.orderservice.enums;

public enum PaymentModeEnum {
	
	CASH("CASH"),
	DEBIT_CARD("DEBIT_CARD"),
	CREDIT_CARD("CREDIT_CARD"),
	PAYPAL("PAYPAL");

	private String code;
	
	private PaymentModeEnum(String name) {
		this.code=name;
	}
	public String getCode() {
		return code;
	}
}

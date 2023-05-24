package com.arcesi.identityservice.enums;

public enum AppUserRole {
	

	CUSTOMER("CUSTOMER"),
	MANAGER("MANAGER"),
	ADMINISTRATEUR("ADMINISTRATEUR");
	
	private final String id;
	
	private AppUserRole(final String id) {
	this.id=id;
	}
	
	public String getId() {
		return id;
	}

}

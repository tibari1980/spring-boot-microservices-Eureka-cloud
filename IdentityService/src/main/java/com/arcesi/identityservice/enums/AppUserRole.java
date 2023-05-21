package com.arcesi.identityservice.enums;

public enum AppUserRole {
	

	CUSTOMER("ROLE_CUSTOMER"),
	MANAGER("ROLE_MANAGER"),
	ADMINISTRATEUR("ROLE_ADMINISTRATEUR");
	
	private final String id;
	
	private AppUserRole(final String id) {
	this.id=id;
	}
	
	public String getId() {
		return id;
	}

}

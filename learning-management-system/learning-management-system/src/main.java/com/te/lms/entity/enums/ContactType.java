package com.te.lms.entity.enums;

public enum ContactType {
	PERSONAL("PERSONAL"),EMERGENCY("EMERGENCY");
	private final String contactType;

	private ContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactType() {
		return contactType;
	}
	
}

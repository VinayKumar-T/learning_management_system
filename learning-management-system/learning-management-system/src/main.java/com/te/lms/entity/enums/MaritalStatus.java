package com.te.lms.entity.enums;

public enum MaritalStatus {
	SINGLE("SINGLE"),MARRIED("MARRIED") ,DIVORSED("DIVORSED");
	private final String martialStatus;

	private MaritalStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}

	public String getMartialStatus() {
		return martialStatus;
	}
	
}

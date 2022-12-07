package com.te.lms.entity.enums;

public enum BatchStatus {
	INPROGRESS("INPROGRESS"),COMPLETED("COMPLETED"),TOBESTARTED("TO_BE_STARTED");
	private final String status;

	private BatchStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
}

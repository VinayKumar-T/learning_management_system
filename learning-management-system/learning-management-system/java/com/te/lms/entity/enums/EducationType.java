package com.te.lms.entity.enums;

public enum EducationType {
	BE("BE"),BTECH("BTECH"),MTECH("MTECH");
	private final String educationType;

	private EducationType(String educationType) {
		this.educationType = educationType;
	}

	public String getEducationType() {
		return educationType;
	}
	
}

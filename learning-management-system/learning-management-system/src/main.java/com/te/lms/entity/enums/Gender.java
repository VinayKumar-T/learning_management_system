package com.te.lms.entity.enums;

public enum Gender {
	MALE("MALE"), FEMALE("FEMALE"), OTHERS("OTHERS");
	
	private final String gender;
	private Gender(String gender) {
		this.gender = gender;
	}
	public String getGenderType() {
		return gender;
	}

}

package com.te.lms.entity.enums;

public enum YearOfExperience {
	ONE(1),TWO(2),THREE(3),FOUR(4);
private final Integer yearOfExperience;



private YearOfExperience(Integer yearOfExperience) {
	this.yearOfExperience = yearOfExperience;
}
public Integer getYearOfExperience() {
	return yearOfExperience;
}
	
}

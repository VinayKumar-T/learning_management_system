package com.te.lms.entity.enums;

public enum EmployeeStatus {
	ACTIVE("ACTIVE"), TERMINATED("TERMINATED"), ABSCOND("ABSCOND");

	private final String employeeStatus;

	private EmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

}

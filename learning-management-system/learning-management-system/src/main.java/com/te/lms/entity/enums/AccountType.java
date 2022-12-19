package com.te.lms.entity.enums;

public enum AccountType {
	SAVINGSACCOUNT("SAVINGSACCOUNT"),SALARYACCOUNT("SALARYACCOUNT");
	private String accountType;

	private AccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	
}

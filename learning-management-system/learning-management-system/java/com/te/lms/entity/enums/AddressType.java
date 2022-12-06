package com.te.lms.entity.enums;

public enum AddressType {
	PERMANENT("PERMANENT"), TEMPORARY("TEMPORARY");
	public String getAddressType() {
		return addressType;
	}

	private final String addressType;

	private AddressType(String addressType) {
		this.addressType = addressType;
	}
	
}

package com.te.lms.entity.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.te.lms.entity.enums.AccountType;
import com.te.lms.entity.enums.AddressType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString

public class BankDetailsDto {
	
	private long accountNo;
	private String bankName;
	@Enumerated(EnumType.STRING)

	private AccountType accountType;
	private String IfscCode;
	private String branch;
	private String state;


}

package com.te.lms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.te.lms.entity.enums.AccountType;
import com.te.lms.entity.enums.AddressType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "bankdetails")
public class BankDetails {
	@Id
	private long accountNo;
	private String bankName;
	@Enumerated(EnumType.STRING)

	private AccountType accountType;
	private String IfscCode;
	private String branch;
	private String state;
	@OneToOne(cascade = CascadeType.ALL)
	private Employee employee;

}

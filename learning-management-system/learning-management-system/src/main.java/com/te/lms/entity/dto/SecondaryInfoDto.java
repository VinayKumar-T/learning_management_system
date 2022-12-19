package com.te.lms.entity.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.checkerframework.common.aliasing.qual.Unique;

import com.te.lms.entity.enums.MaritalStatus;

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
public class SecondaryInfoDto {
	
	@Unique
	private String panNo;
	@Unique
	private String adharNo;

	private String fatherName;

	private String motherName;
	private String spouseName;
	@Unique
	private String passportNo;
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;

}

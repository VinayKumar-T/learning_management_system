package com.te.lms.entity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.te.lms.entity.enums.EmployeeStatus;
import com.te.lms.entity.enums.Gender;

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

public class EmployeeDto {

	private String employeeId;
	
	private String employeeName;
	

	
	private LocalDate dateOfJoining;
	
	private LocalDate dateOfBirth;
	
	private String employeeEmailId;
	
	private String bloodGroup;
	
	private String designation;
	
	private Gender gender;
	
	private String nationality;
	
	private EmployeeStatus employeeStatusDto;
	
	private SecondaryInfoDto secondaryInfoDto;

		
	private List<EducationDetailsDto> educationDetailsDto;
	
	private List<AddressDetailsDto> addressDetailsDto;
	
	private BankDetailsDto bankDetailsDto;
	
	private List<TechnicalSkillsDto> technicalSkillsDto;
	
	private List<ExperienceDto> experienceDto;

	private List<ContactDto> contactDto;
	
	private String password;

}

package com.te.lms.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.google.common.collect.Lists;
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
@Entity
//@Table(name = "employee_primary_info")
public class Employee {
	@Id
	private String employeeId;
	private String employeeName;
	private LocalDate dateOfJoining;
	private LocalDate dateOfBirth;
	private String bloodGroup;
	private String designation;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String nationality;
	@Enumerated(EnumType.STRING)
	private EmployeeStatus employeeStatus;

	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
	private SecondaryInfo secondaryInfo;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EducationDetails> educationDetails;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<AddressDetails> addressDetails;
	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
	private BankDetails bankDetails;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<TechnicalSkills> technicalSkills;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Experience> experience=Lists.newArrayList();
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Contact> contact;
}
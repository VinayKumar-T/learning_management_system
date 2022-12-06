package com.te.lms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.te.lms.entity.enums.EducationType;

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
@Table(name = "education_details")
public class EducationDetails {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer educationDetailsId;
	@Enumerated(EnumType.STRING)
	private EducationType educationType;
	private LocalDate yearOfPassing;
	private String universityName;
	private String instituteName;
	private Double percentage;
	private String specialization;
	private String state;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;

}

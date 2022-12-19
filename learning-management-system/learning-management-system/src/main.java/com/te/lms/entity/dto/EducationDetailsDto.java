package com.te.lms.entity.dto;

import java.awt.geom.Arc2D.Double;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.lms.entity.enums.EducationType;

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
public class EducationDetailsDto {

	@Enumerated(EnumType.STRING)
	private EducationType educationType;
	private LocalDate yearOfPassing;
	private String universityName;
	private String instituteName;
	private Double percentage;
	private String specialization;
	private String state;

}

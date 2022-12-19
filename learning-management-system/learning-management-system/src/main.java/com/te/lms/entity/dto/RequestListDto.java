package com.te.lms.entity.dto;

import java.time.LocalDate;

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
public class RequestListDto {
	

	private String employeeId;
	private String employeeName;
	private LocalDate employeeYop;

	private Double percentage;

	private Integer employeeExperience;
	private Long employeeContactNo;

}


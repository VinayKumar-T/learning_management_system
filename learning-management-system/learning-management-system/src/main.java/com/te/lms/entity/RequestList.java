package com.te.lms.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.checkerframework.common.aliasing.qual.Unique;

import com.te.lms.entity.enums.Action;

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
public class RequestList {
	@Id

	private String employeeId;
	private String employeeName;
	private LocalDate employeeYop;

	private Double percentage;

	private Integer employeeExperience;
	private Long employeeContactNo;

}

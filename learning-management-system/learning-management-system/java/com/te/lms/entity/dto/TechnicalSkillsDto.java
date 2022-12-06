package com.te.lms.entity.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.te.lms.entity.enums.SkillRating;
import com.te.lms.entity.enums.YearOfExperience;

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

public class TechnicalSkillsDto {
	
	private String skillType;
	@Enumerated(EnumType.STRING)
	private SkillRating skillrating;
	@Enumerated(EnumType.ORDINAL)
	private YearOfExperience yearOfExperience;

	
}

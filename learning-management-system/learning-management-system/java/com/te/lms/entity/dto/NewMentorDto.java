package com.te.lms.entity.dto;

import java.util.List;

import com.te.lms.entity.Skills;
import com.te.lms.entity.Technologies;

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

public class NewMentorDto {
	
	private Integer id;
	private String mentorName;
	private String mentorEmployeeId;
	private String mentorEmailId;
	private List<SkillsDto> skillsDto;
	
}
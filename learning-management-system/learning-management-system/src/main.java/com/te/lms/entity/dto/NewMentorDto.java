package com.te.lms.entity.dto;

import java.util.List;

import com.google.common.collect.Lists;

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
	

	private String mentorName;
	private String mentorEmployeeId;
	private String mentorEmailId;
	private List<SkillsDto> skillsDto=Lists.newArrayList();
	
}
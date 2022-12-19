package com.te.lms.entity.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateMentorDto {

	private String mentorName;
	
	private String employeeId;
	
	private String emailId;
	
	private List<SkillsDto> skillsDto;
	
	
}

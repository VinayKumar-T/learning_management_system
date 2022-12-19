package com.te.lms.entity.dto;

import java.time.LocalDate;
import java.util.List;

import com.te.lms.entity.enums.BatchStatus;

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
public class UpdateBatchDto {
	
	
	private String batchName;

	private String mentorName;
	
	private List<TechnologiesDto> technologiesDto;
	
	private LocalDate startDate;
	
	private LocalDate EndDate;
	
	private BatchStatus batchStatus;


}

package com.te.lms.entity.dto;

import java.time.LocalDate;
import java.util.List;

import com.te.lms.entity.Mentor;
import com.te.lms.entity.Technologies;
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
public class NewBatchDto {
	
	private String bathchId;
	
	private String batchName;

	private String mentor;
	
	private List<TechnologiesDto> technologiesDto;
	
	private LocalDate startDate;
	
	private LocalDate EndDate;
	
	private BatchStatus batchStatus;
}
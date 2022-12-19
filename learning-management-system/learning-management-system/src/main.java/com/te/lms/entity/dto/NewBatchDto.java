package com.te.lms.entity.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.te.lms.entity.enums.BatchStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class NewBatchDto {
	
	private String bathchId;
	
	private String batchName;

	private String mentor;
	
	private List<TechnologiesDto> technologiesDto=new ArrayList();
	
	private LocalDate startDate;
	
	private LocalDate EndDate;
	
	private BatchStatus batchStatus;


}

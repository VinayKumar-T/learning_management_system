package com.te.lms.service;

import java.util.List;
import java.util.Optional;

import com.te.lms.entity.dto.ApproveDto;
import com.te.lms.entity.dto.MessageDto;
import com.te.lms.entity.dto.NewBatchDto;
import com.te.lms.entity.dto.NewMentorDto;
import com.te.lms.entity.dto.RejectDto;
import com.te.lms.entity.dto.RequestListDto;
import com.te.lms.entity.dto.UpdateBatchDto;
import com.te.lms.entity.dto.UpdateMentorDto;

public interface AdminService {

	Optional<String> registerMentor(NewMentorDto newMentorDto);

	Optional<String> deleteMentor(String mentorId);

	Optional<String> registerBatch(NewBatchDto newBatchDto);

	Boolean updateBatch(UpdateBatchDto updateBatchDto,String batchId);

	Optional<NewMentorDto> read(String mentorId);

	Optional<NewBatchDto> readBatch(String batchId);

	Optional<MessageDto> approve(String employeeId, ApproveDto approveDto);

	public Optional<List<NewMentorDto>> getMentors();

	Optional<List<NewBatchDto>> getBatches();

	Boolean deleteBatch(String batchId);

	Optional<MessageDto> reject(String employeeId, RejectDto rejectDto);
	
	 Optional<List<RequestListDto>> getRequestList() ;


	Optional<Boolean> updateMentor(UpdateMentorDto updateMentorDto, String empId);

}

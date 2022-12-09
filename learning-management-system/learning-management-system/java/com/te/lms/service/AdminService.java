package com.te.lms.service;

import java.util.List;
import java.util.Optional;

import com.te.lms.entity.Mentor;
import com.te.lms.entity.dto.ApproveDto;
import com.te.lms.entity.dto.MessageDto;
import com.te.lms.entity.dto.NewBatchDto;
import com.te.lms.entity.dto.NewMentorDto;
import com.te.lms.entity.dto.RejectDto;

public interface AdminService {

	Optional<String> registerMentor(NewMentorDto newMentorDto);

	Optional<String> updateMentor(NewMentorDto newMentorDto);

	Optional<String> deleteMentor(String mentorId);

	Optional<String> registerBatch(NewBatchDto newBatchDto);

	Optional<String> updateBatch(NewBatchDto newBatchDto);

	Optional<NewMentorDto> read(String mentorId);

	Optional<NewBatchDto> readBatch(String batchId);

	Optional<MessageDto> approve(String employeeId, ApproveDto approveDto);

	public Optional<List<NewMentorDto>> getMentors();

	Optional<List<NewBatchDto>> getBatches();

	Optional<String> deleteBatch(String batchId);

	Optional<MessageDto> reject(String employeeId, RejectDto rejectDto);
}

package com.te.lms.service;

import java.util.Optional;

import com.te.lms.entity.dto.NewBatchDto;
import com.te.lms.entity.dto.NewMentorDto;

public interface AdminService {

	Optional<String> registerMentor(NewMentorDto newMentorDto);

	Optional<String> updateMentor(NewMentorDto newMentorDto);

	Optional<String> deleteMentor(String mentorId);

	Optional<String> registerBatch(NewBatchDto newBatchDto);

	Optional<String> updateBatch(NewBatchDto newBatchDto);

}

package com.te.lms.service;

import java.util.Optional;

import com.te.lms.entity.dto.NewMentorDto;

public interface AdminService {

	Optional<String> registerMentor(NewMentorDto newMentorDto);

}

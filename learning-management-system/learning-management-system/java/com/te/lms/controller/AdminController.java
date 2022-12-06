package com.te.lms.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.entity.dto.NewMentorDto;
import com.te.lms.mailconfig.Notify;
import com.te.lms.response.ApiResponse;
import com.te.lms.service.AdminService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin")
public class AdminController {
	private final Notify notify;
	private final AdminService adminService;
	@PostMapping(path = "/registermentor")
	public ApiResponse<String> registerMentor(@RequestBody NewMentorDto newMentorDto) {
		Optional<String> mentor=adminService.registerMentor(newMentorDto);
		if(mentor.isPresent()) {
			String message = "Hello "+newMentorDto.getMentorName()+" your role has been updated as an mentor";
			String subject = "Congratulations";
			String emailId = newMentorDto.getMentorEmailId();
			notify.sendEmail(message, emailId, subject);
			return new 	ApiResponse<String>("mentor has been registered successfully", mentor.get());
		}
		
	throw  new RuntimeException("unable to register the mentor");
	}
	
}
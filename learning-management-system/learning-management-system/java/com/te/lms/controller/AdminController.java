package com.te.lms.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.entity.dto.NewBatchDto;
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
		Optional<String> mentor = adminService.registerMentor(newMentorDto);
		if (mentor.isPresent()) {
			String message = "Hello " + newMentorDto.getMentorName() + " your role has been updated as an mentor";
			String subject = "Congratulations";
			String emailId = newMentorDto.getMentorEmailId();
			notify.sendEmail(message, emailId, subject);
			return new ApiResponse<String>("mentor has been registered successfully", mentor.get());
		}

		throw new RuntimeException("unable to register the mentor");
	}

	@PutMapping(path = "/updatementor")
	public ApiResponse<String> updateMentor(@RequestBody NewMentorDto newMentorDto) {
		Optional<String> mentor = adminService.updateMentor(newMentorDto);
		if (mentor.isPresent()) {
			return new ApiResponse<String>("mentor details updated", mentor.get());
		}
		throw new RuntimeException("unable to update details");
	}
	
	
	@PutMapping(path = "/deletementor/{mentorId}")
	public ApiResponse<String> deleteMentor(@PathVariable("mentorId") String mentorId) {
		Optional<String> mentor=adminService.deleteMentor(mentorId);
		if(mentor.isPresent()) {
			return new ApiResponse<String>("mentor deleted", mentorId);

		}
		throw new RuntimeException("unable to delete");
	}
	
	
/////--------------------------------------------------------------------------------------->	

	@PostMapping(path = "/registerbatch")
	public ApiResponse<String> registerBatch(@RequestBody NewBatchDto newBatchDto) {
		Optional<String> batch = adminService.registerBatch(newBatchDto);

		if (batch.isPresent()) {

			return new ApiResponse<String>("batch registerd  successfully  ", newBatchDto.getBathchId());
		}
		throw new RuntimeException("unable to register the mentor");

	}
	@PutMapping(path = "/updatebatch")
	public ApiResponse<String> updateBatch(@RequestBody NewBatchDto newBatchDto) {
		Optional<String>	batch=adminService.updateBatch(newBatchDto);
		
	return null;	
	}
	
}

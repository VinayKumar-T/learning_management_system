package com.te.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.entity.dto.ApproveDto;
import com.te.lms.entity.dto.MessageDto;
import com.te.lms.entity.dto.NewBatchDto;
import com.te.lms.entity.dto.NewMentorDto;
import com.te.lms.entity.dto.RejectDto;
import com.te.lms.entity.dto.RequestListDto;
import com.te.lms.entity.dto.UpdateBatchDto;
import com.te.lms.entity.dto.UpdateMentorDto;
import com.te.lms.exception.BatchDetailsNotFoundException;
import com.te.lms.exception.BatchDetailsNotUpdatedException;
import com.te.lms.exception.BatchesNotFoundException;
import com.te.lms.exception.EmployeeCannotBeApprovedException;
import com.te.lms.exception.EmployeeNotFoundExcpetion;
import com.te.lms.exception.NoDataFoundInTheListException;
import com.te.lms.exception.NoMentorsFoundException;
import com.te.lms.exception.RegistrationFailedException;
import com.te.lms.exception.UnableToDeleteBatchException;
import com.te.lms.exception.UnableToDeleteMentorException;
import com.te.lms.exception.UnableToFindTheEmployeeException;
import com.te.lms.exception.UnableToUpdateMentorException;
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
		Optional<String> mentorName = adminService.registerMentor(newMentorDto);
		if (mentorName.isPresent()) {
			String message = "Hello " + newMentorDto.getMentorName() + " your role has been updated as an mentor";
			String subject = "Congratulations";
			String emailId = newMentorDto.getMentorEmailId();
			notify.sendEmail(message, emailId, subject);
			return new ApiResponse<String>("mentor has been registered successfully", message);
		}

		throw new RegistrationFailedException("Unable to register the mentor please check the details and try again");
	}

	@PutMapping(path = "/update/{empId}")
	public ApiResponse<String> updateMentor(@RequestBody UpdateMentorDto updateMentorDto,
			@PathVariable(name = "empId") String empId) {

		Optional<Boolean> optionalMentorId = adminService.updateMentor(updateMentorDto, empId);
		if (optionalMentorId.get()) {
			return new ApiResponse<String>("mentor details are updated successfully", empId);
		}
		throw new UnableToUpdateMentorException("Unable to update the details please try again");
	}

	@PutMapping(path = "/deletementor/{mentorId}")
	public ApiResponse<String> deleteMentor(@PathVariable("mentorId") String mentorId) {
		Optional<String> mentor = adminService.deleteMentor(mentorId);
		if (mentor.isPresent()) {
			return new ApiResponse<String>("mentor deleted", mentorId);

		}
		throw new UnableToDeleteMentorException("unable to delete the mentor");
	}

	@GetMapping(path = "/searchmentor/{mentorId}")
	public ApiResponse<Object> searchMentor(@PathVariable("mentorId") String mentorId) {
		Optional<NewMentorDto> mentorDto = adminService.read(mentorId);
		if (mentorDto.isPresent()) {
			return new ApiResponse("Mentor Details", mentorDto.get());
		}
		throw new UnableToFindTheEmployeeException("Data not found");
	}

	@GetMapping(path = "/getmentors")
	public ResponseEntity<List<NewMentorDto>> listOfMentors() {
		Optional<List<NewMentorDto>> mentors = adminService.getMentors();
		if (mentors.isPresent()) {
			return ResponseEntity.ok(mentors.get());
		}
		throw new NoMentorsFoundException("no mentors found");
	}

/////--------------------------------------------------------------------------------------->	

	@PostMapping(path = "/registerbatch")
	public ApiResponse<String> registerBatch(@RequestBody NewBatchDto newBatchDto) {
		Optional<String> batch = adminService.registerBatch(newBatchDto);

		if (batch.isPresent()) {

			return new ApiResponse<String>("batch registerd  successfully  ", batch.get());
		}
		throw new RegistrationFailedException("Unable to register the batch");

	}

	@PutMapping(path = "/updatebatch/{batchId}")
	public ApiResponse<String> updateBatch(@PathVariable(name = "batchId") String batchId, // test cases written
			@RequestBody UpdateBatchDto updateBatchDto) {
		Boolean isUpdated = adminService.updateBatch(updateBatchDto, batchId);
		if (isUpdated) {
			return new ApiResponse<String>("batch details has been  updated", batchId);
		}
		throw new BatchDetailsNotUpdatedException("unable to update batch details");

	}

	@GetMapping(path = "/searchbatch/{batchId}")
	public ApiResponse<Object> searchBatch(@PathVariable("batchId") String batchId) {
		Optional<NewBatchDto> batchDto = adminService.readBatch(batchId);
		if (batchDto.isPresent()) {
			return new ApiResponse<Object>("batch returned", batchDto.get());
		}
		throw new BatchDetailsNotFoundException("No batches Found");

	}

	@GetMapping("/getbatches")
	public Optional<List<NewBatchDto>> listOfBatches() {
		Optional<List<NewBatchDto>> batches = adminService.getBatches();
		if (batches.isPresent()) {
			return batches;
		}
		throw new BatchesNotFoundException("No batches Found");

	}

	@PutMapping(path = "/deletebatch/{batchId}")
	public ApiResponse<String> deleteBatch(@PathVariable("batchId") String batchId) {
		Boolean batch = adminService.deleteBatch(batchId);
		if (batch) {
			return new ApiResponse<String>("batch deleted", batchId);

		}
		throw new UnableToDeleteBatchException("unable to delete the batch");
	}

//_-------------------------------------------------------------------------------------------->
	@PostMapping(path = "/approve/employee/{employeeId}")
	public ApiResponse<String> approveEmployee(@PathVariable("employeeId") String employeeId,
			@RequestBody ApproveDto approveDto) {
		Optional<MessageDto> message = adminService.approve(employeeId, approveDto);

		if (message.isPresent()) {
			String msg = message.get().getMessage();
			String subject = "Welcome to technoeleveate";
			String emailId = message.get().getEmailId();
			notify.sendEmail(msg, emailId, subject);
			return new ApiResponse<String>("employee has been approved", employeeId);

		}

		throw new EmployeeCannotBeApprovedException("employee cannot be approved");

	}

	@PutMapping(path = "/reject/employee/{employeeId}")
	public ApiResponse<String> RejectEmployee(@PathVariable("employeeId") String employeeId,
			@RequestBody RejectDto rejectDto) {
		Optional<MessageDto> message = adminService.reject(employeeId, rejectDto);

		if (message.isPresent()) {
			String msg = message.get().getMessage();
			String subject = "Reject mail";
			String emailId = message.get().getEmailId();
			notify.sendEmail(msg, emailId, subject);
			return new ApiResponse<String>("employee has been rejected", employeeId);

		}

		throw new EmployeeNotFoundExcpetion("unable to find the employee");

	}
	@GetMapping(path = "/requestlist")
	public ResponseEntity<List<RequestListDto>> getRequestList() { // test cases written
		Optional<List<RequestListDto>> optEmployees = adminService.getRequestList();
		if (optEmployees.isPresent()) {
			return ResponseEntity.ok(optEmployees.get());
		}
		throw new NoDataFoundInTheListException("List is Empty");

	}

//-----------------------------------------------------------------------------------------------

}

package com.te.lms.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.entity.dto.EmployeeDto;
import com.te.lms.response.ApiResponse;
import com.te.lms.service.EmployeeService;
import com.te.lms.service.RequestListService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/public")
public class EmployeeController {
	private final EmployeeService employeeService;
	private final RequestListService requestListService;

	@PostMapping(path = "/employee/register")
	public ApiResponse<String> registerEmployee(@RequestBody EmployeeDto employeeDto) {
		Optional<String> employeeId = employeeService.register(employeeDto);
		if (employeeId.isPresent()) {
			requestListService.addToRequestList(employeeId.get());
		}
		return new ApiResponse<String>("your request will be approved in sometime, please wait", null);
	}
}

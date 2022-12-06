package com.te.lms.service;

import java.util.Optional;

import com.te.lms.entity.dto.EmployeeDto;

public interface EmployeeService {

	Optional<String> register(EmployeeDto employeeDto);

}

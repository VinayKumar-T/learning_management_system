package com.te.lms.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.te.lms.entity.Employee;
import com.te.lms.entity.RequestList;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.repository.RequestRepository;
import com.te.lms.service.RequestListService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RequestListServiceImpl implements RequestListService {
	private final EmployeeRepository employeeRepository;
	private final RequestRepository requestRepository;

	@Override
	public void addToRequestList(String employeeId) {
		
		Optional<Employee> emp = employeeRepository.findById(employeeId);
		if(emp.isPresent()) {
			Employee employee=emp.get();
			RequestList requestList=new RequestList();
			requestList.setEmployeeId(employee.getEmployeeId());
			requestList.setEmployeeName(employee.getEmployeeName());
		requestList.setPercentage(employee.getEducationDetails().get(0).getPercentage());
		requestList.setEmployeeContactNo(employee.getContact().get(0).getContactNumber());
		requestList.setEmployeeYop(employee.getEducationDetails().get(0).getYearOfPassing());
		Optional<Integer> totalExperiece = employeeRepository.getTotalExperiece(employeeId);
		
		requestList.setEmployeeExperience(totalExperiece.get());
		
		requestRepository.save(requestList);

		}
	}

}

package com.te.lms.service.impl;

import java.beans.Beans;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.te.lms.entity.AddressDetails;
import com.te.lms.entity.BankDetails;
import com.te.lms.entity.Contact;
import com.te.lms.entity.EducationDetails;
import com.te.lms.entity.Employee;
import com.te.lms.entity.Experience;
import com.te.lms.entity.SecondaryInfo;
import com.te.lms.entity.TechnicalSkills;
import com.te.lms.entity.dto.AddressDetailsDto;
import com.te.lms.entity.dto.BankDetailsDto;
import com.te.lms.entity.dto.ContactDto;
import com.te.lms.entity.dto.EducationDetailsDto;
import com.te.lms.entity.dto.EmployeeDto;
import com.te.lms.entity.dto.ExperienceDto;
import com.te.lms.entity.dto.SecondaryInfoDto;
import com.te.lms.entity.dto.TechnicalSkillsDto;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository employeeRepository;

	@Override
	public Optional<String> register(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		SecondaryInfo secondaryInfo = new SecondaryInfo();
		BankDetails bankDetails = new BankDetails();
		List<EducationDetails> educationDetails = Lists.newArrayList();
		List<AddressDetails> addressDetails = Lists.newArrayList();
		List<TechnicalSkills> technicalSkills = Lists.newArrayList();
		List<Experience> experience = Lists.newArrayList();
		List<Contact> contact = Lists.newArrayList();

		BeanUtils.copyProperties(employeeDto, employee);
		BeanUtils.copyProperties(employeeDto.getSecondaryInfoDto(), secondaryInfo);
		employee.setSecondaryInfo(secondaryInfo);
		secondaryInfo.setEmployee(employee);
		
		BeanUtils.copyProperties(employeeDto.getBankDetailsDto(), bankDetails);
		employee.setBankDetails(bankDetails);
		bankDetails.setEmployee(employee);

		for (EducationDetailsDto educationDetailsDto : employeeDto.getEducationDetailsDto()) {
			EducationDetails ed = new EducationDetails();
			BeanUtils.copyProperties(educationDetailsDto, ed);
			ed.setEmployee(employee);

			educationDetails.add(ed);
			}	
		employee.setEducationDetails(educationDetails);
		
		for (AddressDetailsDto  addressDto : employeeDto.getAddressDetailsDto()) {
			AddressDetails ad=new AddressDetails();
			BeanUtils.copyProperties(addressDto, ad);
			ad.setEmployee(employee);
			addressDetails.add(ad);
		}
		employee.setAddressDetails(addressDetails);
		
		for (TechnicalSkillsDto technicalSkillsDto : employeeDto.getTechnicalSkillsDto()) {
			TechnicalSkills ts=new TechnicalSkills();
			BeanUtils.copyProperties(technicalSkillsDto, ts);
			ts.setEmployee(employee);
			technicalSkills.add(ts);
			
		}
		employee.setTechnicalSkills(technicalSkills);
		
		for (ExperienceDto experienceDto : employeeDto.getExperienceDto()) {
			Experience ex=new Experience();
			BeanUtils.copyProperties(experienceDto, ex);
			ex.setEmployee(employee);
			experience.add(ex);
		}
		employee.setExperience(experience);
		
		for (ContactDto contactDto : employeeDto.getContactDto()) {
			Contact c=new Contact();
			BeanUtils.copyProperties(contactDto, c);
			c.setEmployee(employee);
			contact.add(c);
		}
		employee.setContact(contact);
		
		Employee emp = employeeRepository.save(employee);
		
		return Optional.ofNullable(emp.getEmployeeId());
	}

}

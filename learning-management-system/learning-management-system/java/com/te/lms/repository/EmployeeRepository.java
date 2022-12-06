package com.te.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.te.lms.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{
	@Query(value = "SELECT SUM(years_of_experience) FROM employee_experience_info WHERE employee_fk=?1" , nativeQuery = true)
	Optional<Integer> getTotalExperiece(String employeeId);
}


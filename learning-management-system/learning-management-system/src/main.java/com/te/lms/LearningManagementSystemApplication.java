package com.te.lms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.collect.Lists;
import com.te.lms.entity.Admin;
import com.te.lms.entity.AppUser;
import com.te.lms.entity.Roles;
import com.te.lms.repository.AdminRepository;
import com.te.lms.repository.AppUserRepository;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class LearningManagementSystemApplication {
	private final AdminRepository adminRepository;
	private final RoleRepository roleRepository;
	private final AppUserRepository appUserRepository;
	private final EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(LearningManagementSystemApplication.class, args);
	} 
	@Bean
	public CommandLineRunner runner() {
		return args -> {
			Roles employee = Roles.builder().roleName("ROLE_EMPLOYEE").build();
			Roles mentor = Roles.builder().roleName("ROLE_MENTOR").build();
			Roles admin = Roles.builder().roleName("ROLE_ADMIN").appUser(Lists.newArrayList()).build();

			Admin admin01 = Admin.builder().adminId("Ty001").adminName("head").build();
			AppUser adminCredentials = AppUser.builder().userName(admin01.getAdminName()).password("qwerty")
					.roles(Lists.newArrayList()).build();

			roleRepository.save(employee);
			roleRepository.save(mentor);

			adminRepository.save(admin01);
			adminCredentials.getRoles().add(admin);
			admin.getAppUser().add(adminCredentials);

			roleRepository.save(admin);

			appUserRepository.save(adminCredentials);
		};

	}

}

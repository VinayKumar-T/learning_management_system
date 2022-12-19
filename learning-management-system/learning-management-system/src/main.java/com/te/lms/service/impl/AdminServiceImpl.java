package com.te.lms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.te.lms.entity.AppUser;
import com.te.lms.entity.Batch;
import com.te.lms.entity.Employee;
import com.te.lms.entity.Mentor;
import com.te.lms.entity.RequestList;
import com.te.lms.entity.Roles;
import com.te.lms.entity.Skills;
import com.te.lms.entity.Technologies;
import com.te.lms.entity.dto.ApproveDto;
import com.te.lms.entity.dto.MessageDto;
import com.te.lms.entity.dto.NewBatchDto;
import com.te.lms.entity.dto.NewMentorDto;
import com.te.lms.entity.dto.RejectDto;
import com.te.lms.entity.dto.RequestListDto;
import com.te.lms.entity.dto.SkillsDto;
import com.te.lms.entity.dto.TechnologiesDto;
import com.te.lms.entity.dto.UpdateBatchDto;
import com.te.lms.entity.dto.UpdateMentorDto;
import com.te.lms.entity.enums.BatchStatus;
import com.te.lms.entity.enums.EmployeeStatus;
import com.te.lms.repository.AppUserRepository;
import com.te.lms.repository.BatchRepository;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.repository.MentorRepositorty;
import com.te.lms.repository.RequestRepository;
import com.te.lms.repository.RoleRepository;
import com.te.lms.repository.TechnologiesRepository;
import com.te.lms.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	private final MentorRepositorty mentorRepository;
	private final RequestRepository requestRepository;
	private final BatchRepository batchRepository;
	private final EmployeeRepository employeeRepository;
	private final RoleRepository roleRepository;
	private final AppUserRepository appUserRepository;
	private final TechnologiesRepository technologiesRepository;

	@Override
	public Optional<String> registerMentor(NewMentorDto newMentorDto) {
		Mentor mentor = new Mentor();
		BeanUtils.copyProperties(newMentorDto, mentor);

		List<Skills> skills = Lists.newArrayList();
		for (SkillsDto skillsdto : newMentorDto.getSkillsDto()) {
			Skills sk = new Skills();
			BeanUtils.copyProperties(skillsdto, sk);

			skills.add(sk);
			sk.setMentor(mentor);
		}
		mentor.setSkills(skills);

		mentor.setStatus("Active");

		Mentor savedmentor = mentorRepository.save(mentor);
		return Optional.ofNullable(savedmentor.getMentorName());
	}

	

	@Override
	public Optional<String> deleteMentor(String mentorId) {
		Optional<Mentor> mentorFromDb = mentorRepository.findById(mentorId);
		mentorFromDb.get().setStatus("INACTIVE");
		
		mentorRepository.save(mentorFromDb.get());
		return Optional.ofNullable(mentorFromDb.get().getMentorId());

	}

	@Override
	public Optional<List<NewBatchDto>> getBatches() {
		List<Batch> batches = batchRepository.findAll();
		List<NewBatchDto> newBatchDtos = Lists.newArrayList();

		if (batches != null) {
			List<NewBatchDto> nbd = Lists.newArrayList();
			for (Batch batch : batches) {
				NewBatchDto newBatchDto = new NewBatchDto();
				BeanUtils.copyProperties(batch, newBatchDto);
				List<TechnologiesDto> technologiesDtos = Lists.newArrayList();
				for (Technologies technologies : batch.getTechnologies()) {
					TechnologiesDto technologiesDto2 = new TechnologiesDto();
					BeanUtils.copyProperties(technologies, technologiesDto2);
					technologiesDtos.add(technologiesDto2);
				}
				newBatchDto.setTechnologiesDto(technologiesDtos);
				newBatchDtos.add(newBatchDto);

				nbd.add(newBatchDto);
			}
		}

		return Optional.ofNullable(newBatchDtos);
	}

	@Override
	public Optional<NewMentorDto> read(String mentorId) {
		Optional<Mentor> mentorFromDb = mentorRepository.findById(mentorId);
		if (mentorFromDb.isPresent()) {
			Mentor mentor = mentorFromDb.get();
			NewMentorDto nmd = new NewMentorDto();

			BeanUtils.copyProperties(mentor, nmd);

			List<SkillsDto> skillDto = Lists.newArrayList();

			for (Skills skills2 : mentorFromDb.get().getSkills()) {
				SkillsDto sd = new SkillsDto();
				BeanUtils.copyProperties(skills2, sd);
				skillDto.add(sd);
			}
			nmd.setSkillsDto(skillDto);
			return Optional.ofNullable(nmd);

		}
		throw new RuntimeException("mentor not found");

	}

	// -------------------------------------------------------------------------------------->

	@Override
	public Optional<String> registerBatch(NewBatchDto newBatchDto) {

		Batch batch = new Batch();
		BeanUtils.copyProperties(newBatchDto, batch);

		List<Technologies> technologies = Lists.newArrayList();
		for (TechnologiesDto technologiesDto : newBatchDto.getTechnologiesDto()) {
			Technologies te = new Technologies();
			BeanUtils.copyProperties(technologiesDto, te);
			te.getBatch().add(batch);
			technologies.add(te);
		}
		Optional<Mentor> mentor = mentorRepository.findByMentorName(newBatchDto.getMentor());
		batch.setMentor(mentor.get());
		batch.setTechnologies(technologies);
		batchRepository.save(batch);

		return Optional.ofNullable(newBatchDto.getBathchId());

	}

	@Override
	public Boolean updateBatch( UpdateBatchDto updateBatchDto,String batchId) {

		Optional<Batch> optBatch = batchRepository.findById(batchId);
		if (optBatch.isPresent()) {
			Batch batch = optBatch.get();
			BeanUtils.copyProperties(updateBatchDto, batch);

			List<Technologies> technologies1 = batch.getTechnologies();
		technologiesRepository.deleteAll(technologies1);

			for (TechnologiesDto technologiesDto : updateBatchDto.getTechnologiesDto()) {
				Technologies technologies = new Technologies();
				BeanUtils.copyProperties(technologiesDto, technologies);
				technologies1.add(technologies);
				technologies.getBatch().add(batch);
			}
			Optional<Mentor> mentor = mentorRepository.findByMentorName(updateBatchDto.getMentorName());
			if (mentor.isPresent()) {
				batch.setMentor(mentor.get());
				mentor.get().setBatch(batch);
			}
			
			batch.setTechnologies(technologies1);
			batchRepository.save(batch);
			return true;
		}
		return false;
	}

	@Override
	public Optional<NewBatchDto> readBatch(String batchId) {
		Optional<Batch> batchFromDb = batchRepository.findById(batchId);
		NewBatchDto newBatchDto = new NewBatchDto();

		if (batchFromDb.isPresent()) {
			BeanUtils.copyProperties(batchFromDb.get(), newBatchDto);
			List<TechnologiesDto> technologiesDto = Lists.newArrayList();
			for (Technologies technologies : batchFromDb.get().getTechnologies()) {
				TechnologiesDto td = new TechnologiesDto();
				BeanUtils.copyProperties(technologies, td);
				technologiesDto.add(td);
			}
			newBatchDto.setTechnologiesDto(technologiesDto);

		}
		return Optional.ofNullable(newBatchDto);
	}

	@Override
	public Optional<MessageDto> approve(String employeeId, ApproveDto approveDto) {
		Optional<RequestList> reqDb = requestRepository.findById(employeeId);
		if (reqDb.isPresent()) {
			Optional<Employee> empDb = employeeRepository.findById(employeeId);
			if (empDb.isPresent()) {
				Optional<Roles> roleFromDb = roleRepository.findByRoleName("ROLE_Employee");
				if (roleFromDb.isPresent()) {
					AppUser appUser = AppUser.builder().userName(employeeId).password("welcome")
							.roles(Lists.newArrayList()).build();
					appUser.getRoles().add(roleFromDb.get());
					Optional<Batch> batch = batchRepository.findById(approveDto.getBatchId());
					empDb.get().setBatch(batch.get());
					batch.get().getEmployee().add(empDb.get());
					empDb.get().setEmployeeStatus(EmployeeStatus.ACTIVE);
					employeeRepository.save(empDb.get());
					appUserRepository.save(appUser);
					batchRepository.save(batch.get());
					requestRepository.deleteById(employeeId);
					
					String message = "Hello " + empDb.get().getEmployeeName() + "\n"
							+ "Welcome to the team of techno Elevate " + "\n" + "username " + appUser.getUserName()
							+ "  password  " + appUser.getPassword();
					MessageDto md = MessageDto.builder().emailId(empDb.get().getEmployeeEmailId()).message(message)
							.build();

					return Optional.ofNullable(md);

				}
			}
		}
		throw new RuntimeException("unable approve");
	}

	@Override
	public Optional<List<NewMentorDto>> getMentors() {
		List<Mentor> mentorsFromDb = mentorRepository.findAll();
		if (mentorsFromDb!=null) {
			List<NewMentorDto> mentorDto = Lists.newArrayList();
			for (Mentor mentor : mentorsFromDb) {
				NewMentorDto mentorDto2 = new NewMentorDto();
				BeanUtils.copyProperties(mentor, mentorDto2);
				for (Skills skills : mentor.getSkills()) {
					SkillsDto skillsDto2 = new SkillsDto();
					BeanUtils.copyProperties(skills, skillsDto2);
					mentorDto2.getSkillsDto().add(skillsDto2);
					
				} 
				mentorDto.add(mentorDto2);
			}
			return Optional.ofNullable(mentorDto);
		}
		return Optional.ofNullable(null);
	}

	@Override
	public Boolean deleteBatch(String batchId) {
	Optional<Batch>	batch=batchRepository.findById(batchId);
	if(batch.isPresent()) {
	NewBatchDto nbd=new NewBatchDto();
	BeanUtils.copyProperties(batch, nbd);
	batch.get().setBatchStatus(BatchStatus.INACTIVE);
	batchRepository.save(batch.get());
	return true;
	}
		return false;
	
	}

	@Override
	public Optional<MessageDto> reject(String employeeId, RejectDto rejectDto) {
		Optional<RequestList> reqDb = requestRepository.findById(employeeId);
		if(reqDb.isPresent()) {
			Optional<Employee> empDb = employeeRepository.findById(employeeId);
			if(empDb.isPresent()) {
				
					empDb.get().setStatus("InacTive");
					requestRepository.deleteById(employeeId);
					String message="Hello "+reqDb.get().getEmployeeName()+"\n"
							+"we regrete to inform you that you are not allowed to further process"+rejectDto.getMessage();
			MessageDto md=MessageDto.builder().message(message).emailId(empDb.get().getEmployeeEmailId()).build();
			
			return Optional.of(md);
			}
		}
		throw new RuntimeException("not found");
	}



	@Override
	public Optional<Boolean> updateMentor(UpdateMentorDto updateMentorDto, String empId) {
		Optional<Mentor> mentorFromDB = mentorRepository.findById(empId);
		if (mentorFromDB.isPresent()) {
			Mentor mentor = mentorFromDB.get();
			mentor.setEmailId(updateMentorDto.getEmailId());
			mentor.setMentorName(updateMentorDto.getMentorName());
			List<Skills> skills = mentor.getSkills();
			List<Skills> skills2 = Lists.newArrayList();
			if (skills != null) {
				for (SkillsDto skillsDto : updateMentorDto.getSkillsDto()) {
					Skills skillsEntity = new Skills();
					BeanUtils.copyProperties(skillsDto, skillsEntity);
					skills2.add(skillsEntity);
					skillsEntity.setMentor(mentor);
					
				}
				mentor.setSkills(skills2);
			}
			mentorRepository.save(mentor);
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(false);
	}
	@Override
	public Optional<List<RequestListDto>> getRequestList() {
		List<RequestList> employees = requestRepository.getList();
		if (employees != null) {
			List<RequestListDto> requestListDto = Lists.newArrayList();
			for (RequestList requestsLists : employees) {
				RequestListDto requestsListsDto2 = new RequestListDto();
				BeanUtils.copyProperties(requestsLists, requestsListsDto2);
				requestListDto.add(requestsListsDto2);
			}
			return Optional.ofNullable(requestListDto);
		}
		return null;
	}
	

}

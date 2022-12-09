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
import com.te.lms.entity.dto.SkillsDto;
import com.te.lms.entity.dto.TechnologiesDto;
import com.te.lms.entity.enums.EmployeeStatus;
import com.te.lms.repository.AppUserRepository;
import com.te.lms.repository.BatchRepository;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.repository.MentorRepositorty;
import com.te.lms.repository.RequestRepository;
import com.te.lms.repository.RoleRepository;
import com.te.lms.repository.UpdateMentorRepositorty;
import com.te.lms.service.AdminService;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Inactive;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	private final MentorRepositorty mentorRepository;
	private final RequestRepository requestRepository;
	private final BatchRepository batchRepository;
	private final EmployeeRepository employeeRepository;
	private final RoleRepository roleRepository;
	private final AppUserRepository appUserRepository;

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
	public Optional<String> updateMentor(NewMentorDto newMentorDto) {

		Mentor mentor = new Mentor();
		BeanUtils.copyProperties(newMentorDto, mentor);
		List<Skills> skills = Lists.newArrayList();

		Optional<Mentor> mentorFromDb = mentorRepository.findById(newMentorDto.getMentorEmployeeId());
		if (mentorFromDb.isPresent()) {
			mentorFromDb.get().setMentorEmployeeId(newMentorDto.getMentorEmployeeId());
			mentorFromDb.get().setMentorName(newMentorDto.getMentorName());
			mentorFromDb.get().setMentorEmailId(newMentorDto.getMentorEmailId());

			for (SkillsDto nmd : newMentorDto.getSkillsDto()) {
				Skills sk = new Skills();
				BeanUtils.copyProperties(nmd, sk);
				mentorFromDb.get().setSkills(skills);
			}
			Mentor savedmentor = mentorRepository.save(mentorFromDb.get());

		}

		return Optional.ofNullable(mentorFromDb.get().getMentorEmployeeId());
	}

	@Override
	public Optional<String> deleteMentor(String mentorId) {
		Optional<Mentor> mentorFromDb = mentorRepository.findById(mentorId);
		mentorFromDb.get().setStatus("INACTIVE");
		mentorRepository.save(mentorFromDb.get());
		return Optional.ofNullable(mentorFromDb.get().getMentorEmployeeId());

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
		batch.setTechnologies(technologies);
		batchRepository.save(batch);

		return Optional.ofNullable(newBatchDto.getBatchName());

	}

	@Override
	public Optional<String> updateBatch(NewBatchDto newBatchDto) {
		Batch batch = new Batch();
		BeanUtils.copyProperties(newBatchDto, batch);
		Mentor mentor = new Mentor();
		BeanUtils.copyProperties(newBatchDto.getMentor(), mentor);
		batch.setMentor(mentor);
		mentor.setBatch(batch);

		List<Technologies> technologies = Lists.newArrayList();

		Optional<Batch> batchFromDb = batchRepository.findById(newBatchDto.getBathchId());
		if (batchFromDb.isPresent()) {
			batchFromDb.get().setBatchName(newBatchDto.getBatchName());
			batchFromDb.get().setBatchStatus(newBatchDto.getBatchStatus());
			batchFromDb.get().setEndDate(newBatchDto.getEndDate());
			batchFromDb.get().setStartDate(newBatchDto.getStartDate());
			for (TechnologiesDto technologiesDto : newBatchDto.getTechnologiesDto()) {
				Technologies te = new Technologies();
				BeanUtils.copyProperties(technologiesDto, te);
				technologies.add(te);
				batchFromDb.get().setTechnologies(technologies);
			}
			// batchFromDb.get().setMentor(newBatchDto.getMentor());
			batchRepository.save(batchFromDb.get());

		}

		return Optional.ofNullable(batchFromDb.get().getBathchId());
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
		List<Mentor> allDb = mentorRepository.findAll();
		if (allDb != null) {
			List<NewMentorDto> nm = Lists.newArrayList();
			for (Mentor mentor : allDb) {
				NewMentorDto nmd = new NewMentorDto();
				BeanUtils.copyProperties(mentor, nmd);
				List<SkillsDto> skillsDto = Lists.newArrayList();
				for (Skills skills : mentor.getSkills()) {
					SkillsDto sd = new SkillsDto();
					BeanUtils.copyProperties(skills, sd);
					skillsDto.add(sd);

				}
				nmd.setSkillsDto(skillsDto);
				nm.add(nmd);

			}
			return Optional.ofNullable(nm);

		}

		throw new RuntimeException("unable to fetch");
	}

	@Override
	public Optional<String> deleteBatch(String batchId) {
	Optional<Batch>	batch=batchRepository.findById(batchId);
	NewBatchDto nbd=new NewBatchDto();
	BeanUtils.copyProperties(batch, nbd);
	batch.get().setStatus("InActive");
	batchRepository.save(batch.get());
	
		return Optional.ofNullable(batch.get().getBatchName());
	}

	@Override
	public Optional<MessageDto> reject(String employeeId, RejectDto rejectDto) {
		Optional<RequestList> reqDb = requestRepository.findById(employeeId);
		if(reqDb.isPresent()) {
			Optional<Employee> empDb = employeeRepository.findById(employeeId);
			if(empDb.isPresent()) {
					empDb.get().setStatus("InActive");
					requestRepository.deleteById(employeeId);
					String message="Hello "+reqDb.get().getEmployeeName()+"\n"
							+"we regrete to inform you that you are not allowed to further process"+rejectDto.getMessage();
			MessageDto md=MessageDto.builder().message(message).emailId(empDb.get().getEmployeeEmailId()).build();
			
			return Optional.of(md);
			}
		}
		throw new RuntimeException("not found");
	}
	

}

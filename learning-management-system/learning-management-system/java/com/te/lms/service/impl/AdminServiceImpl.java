package com.te.lms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.te.lms.entity.Batch;
import com.te.lms.entity.Mentor;
import com.te.lms.entity.Skills;
import com.te.lms.entity.Technologies;
import com.te.lms.entity.dto.NewBatchDto;
import com.te.lms.entity.dto.NewMentorDto;
import com.te.lms.entity.dto.SkillsDto;
import com.te.lms.entity.dto.TechnologiesDto;
import com.te.lms.repository.BatchRepository;
import com.te.lms.repository.MentorRepositorty;
import com.te.lms.repository.UpdateMentorRepositorty;
import com.te.lms.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	private final MentorRepositorty mentorRepository;

	private final BatchRepository batchRepository;

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
	
	//-------------------------------------------------------------------------------------->

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
		Batch batch=new Batch();
		BeanUtils.copyProperties(newBatchDto, batch);
		Mentor mentor=new Mentor();
		BeanUtils.copyProperties(newBatchDto.getMentor(), mentor);
		batch.setMentor(mentor);
		mentor.setBatch(batch);
		
		List<Technologies> technologies=Lists.newArrayList();
		
	//	batchRepository.findById(newBatchDto.getBathchId());
		
		
		return null;
	}

	

	

}

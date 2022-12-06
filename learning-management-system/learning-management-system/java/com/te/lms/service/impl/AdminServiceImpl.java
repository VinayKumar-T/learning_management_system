package com.te.lms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.te.lms.entity.Mentor;
import com.te.lms.entity.Skills;
import com.te.lms.entity.dto.NewMentorDto;
import com.te.lms.entity.dto.SkillsDto;
import com.te.lms.repository.MentorRepositorty;
import com.te.lms.service.AdminService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
	private final MentorRepositorty mentorRepository;
	@Override
	public Optional<String> registerMentor(NewMentorDto newMentorDto) {
		Mentor mentor=new Mentor();
		BeanUtils.copyProperties(newMentorDto, mentor);
		List<Skills> skills=Lists.newArrayList();
		for (SkillsDto skillsdto: newMentorDto.getSkillsDto()) {
			Skills sk=new Skills();
			BeanUtils.copyProperties(skillsdto, sk);
			
			skills.add(sk);
			sk.setMentor(mentor);
		}
		mentor.setSkills(skills);
		Mentor savedmentor = mentorRepository.save(mentor);
		return Optional.ofNullable(savedmentor.getMentorName());
	}

}
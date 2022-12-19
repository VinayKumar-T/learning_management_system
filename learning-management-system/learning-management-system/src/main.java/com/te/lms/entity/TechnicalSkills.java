package com.te.lms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.te.lms.entity.enums.SkillRating;
import com.te.lms.entity.enums.YearOfExperience;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "technical_skils")
public class TechnicalSkills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer technicalSkillsId;
	private String skillType;
	@Enumerated(EnumType.STRING)
	private SkillRating skillrating;
	@Enumerated(EnumType.ORDINAL)
	private YearOfExperience yearOfExperience;

	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
}

package com.te.lms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="mentor_skills")
public class Skills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sno;
	
	private String skillName;
	
	@ManyToOne
	private Mentor mentor;
}

package com.te.lms.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.google.common.collect.Lists;
import com.te.lms.entity.enums.BatchStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String bathchId;

	private String batchName;
	@OneToOne
	@JoinColumn(name = "mentor_fk")
	private Mentor mentor;

	@ManyToMany(mappedBy = "batch", cascade = CascadeType.ALL)
	private List<Technologies> technologies;

	private LocalDate startDate;

	private LocalDate EndDate;
	@Enumerated(EnumType.STRING)
	private BatchStatus batchStatus;

	@OneToMany(mappedBy = "batch")
	private List<Employee> employee = Lists.newArrayList();

}

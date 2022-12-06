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

import com.te.lms.entity.enums.ContactType;

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
@Table(name = "contact")
public class Contact {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer expId;
	@Enumerated(EnumType.STRING)
	private ContactType contactType;
	
	private Long contactNumber;
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
}

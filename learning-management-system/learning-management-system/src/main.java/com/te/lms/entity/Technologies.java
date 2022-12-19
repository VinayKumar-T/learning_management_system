package com.te.lms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

import com.google.common.collect.Lists;

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
public class Technologies {
	@Id

	
	private String technology;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "map_technology_batch", joinColumns = @JoinColumn(name = "technology_fk"), inverseJoinColumns = @JoinColumn(name = "batch_fk"))
	private List<Batch> batch = Lists.newArrayList();

	
}

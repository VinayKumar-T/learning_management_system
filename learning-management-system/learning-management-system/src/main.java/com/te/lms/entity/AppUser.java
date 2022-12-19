package com.te.lms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
public class AppUser {
	@Id
	private String userName;
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "map_roles_appuser",
	joinColumns=@JoinColumn(name = "userfk")
	,inverseJoinColumns =@JoinColumn(name="role_fk"))
	private List<Roles> roles=Lists.newArrayList();
}

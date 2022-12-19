package com.te.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.te.lms.entity.Mentor;
@Repository
public interface MentorRepositorty extends JpaRepository<Mentor,String>{

	Optional<Mentor> findByMentorName(String mentor);

	@Query(value = "select * from mentor where status='ACTIVE'",nativeQuery = true)
	Optional<List<Mentor>> getMentors();

}

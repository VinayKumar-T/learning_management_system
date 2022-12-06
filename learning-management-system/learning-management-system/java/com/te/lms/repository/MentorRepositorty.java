package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lms.entity.Mentor;
@Repository
public interface MentorRepositorty extends JpaRepository<Mentor,Integer>{

}

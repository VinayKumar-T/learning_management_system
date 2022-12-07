package com.te.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lms.entity.Mentor;
@Repository
public interface UpdateMentorRepositorty extends JpaRepository<Mentor , Mentor> {


}

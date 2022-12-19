package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lms.entity.Technologies;
@Repository
public interface TechnologiesRepository extends JpaRepository<Technologies, Integer>{

}

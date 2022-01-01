package com.greatlearning.StudentMgmPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.StudentMgmPortal.entity.Students;


public interface StudentsRepository extends JpaRepository<Students, Integer> {
	

	List<Students> findByNameContainsAndCountryContainsAllIgnoreCase(String name,String country);
	
}

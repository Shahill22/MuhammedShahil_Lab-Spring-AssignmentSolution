package com.greatlearning.StudentMgmPortal.service;



import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.greatlearning.StudentMgmPortal.repository.StudentsRepository;
import com.greatlearning.StudentMgmPortal.entity.Students;

import java.util.List;




@Repository
public class StudentsServiceImpl implements StudentsService {


	@Autowired
	StudentsRepository StudentsRepository;

	@Override
	public List<Students> findAll() {
		// TODO Auto-generated method stub
		List<Students> students=StudentsRepository.findAll();
		return students;
	}

	@Override
	public Students findById(int theId) {
		// TODO Auto-generated method stub
		return StudentsRepository.findById(theId).get();
	}

	@Override
	public void save(Students theStudents) {
		// TODO Auto-generated method stub
		StudentsRepository.save(theStudents);
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		StudentsRepository.deleteById(theId);
		
	}

	@Override
	public List<Students> searchBy(String name, String country) {
		// TODO Auto-generated method stub
		List<Students> students=StudentsRepository.findByNameContainsAndCountryContainsAllIgnoreCase(name, country);

		return students;
	}





}
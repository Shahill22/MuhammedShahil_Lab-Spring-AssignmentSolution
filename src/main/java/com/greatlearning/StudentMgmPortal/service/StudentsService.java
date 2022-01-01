package com.greatlearning.StudentMgmPortal.service;

import java.util.List;



import com.greatlearning.StudentMgmPortal.entity.Students;


public interface StudentsService {
	public List<Students> findAll();

	public Students findById(int theId);

	public void save(Students theStudent);

	public void deleteById(int theId);

	public List<Students> searchBy(String name, String country);

}

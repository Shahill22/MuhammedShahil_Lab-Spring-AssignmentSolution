package com.greatlearning.StudentMgmPortal.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.StudentMgmPortal.entity.Students;
import com.greatlearning.StudentMgmPortal.service.StudentsService;




@Controller
@RequestMapping("/students")
public class StudentsController {

	@Autowired
	private StudentsService studentsService;



	// add mapping for "/list"

	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		

		// get Books from db
		List<Students> theStudents = studentsService.findAll();

		// add to the spring model
		theModel.addAttribute("Students", theStudents);
		

		return "list-Students";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Students theStudents = new Students();

		theModel.addAttribute("Students", theStudents);

		return "Students-form";
	}

	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentsId") int theId,
			Model theModel) {

		// get the Book from the service
		Students theStudents = studentsService.findById(theId);


		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Students", theStudents);

		// send over to our form
		return "Students-form";			
	}


	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id,
			@RequestParam("name") String name,@RequestParam("department") String department,@RequestParam("country") String country) {

		System.out.println(id);
		Students theStudents;
		if(id!=0)
		{
			theStudents=studentsService.findById(id);
			theStudents.setName(name);
			theStudents.setDepartment(department);
			theStudents.setCountry(country);
		}
		else
			theStudents=new Students(name, department, country);
//		// save the Book
		studentsService.save(theStudents);


		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list";

	}

	

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentsId") int theId) {

		// delete the Students
		studentsService.deleteById(theId);

		// redirect to /Students/list
		return "redirect:/students/list";

	}


	@RequestMapping("/search")
	public String search(@RequestParam("name") String name,
			@RequestParam("country") String country,
			Model theModel) {

		// check names, if both are empty then just give list of all Students

		if (name.trim().isEmpty() && country.trim().isEmpty()) {
			return "redirect:/students/list";
		}
		else {
			// else, search by first name and last name
			List<Students> theStudents =
					studentsService.searchBy(name, country);

			// add to the spring model
			theModel.addAttribute("Students", theStudents);

			// send to list-Students
			return "list-Students";
		}

	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
}



















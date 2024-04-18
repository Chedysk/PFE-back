package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.application.model.Professor;
import com.application.model.User;
import com.application.services.ProfessorService;
import com.application.services.UserService;

@RestController
@RequestMapping("/api")
public class LoginController
{
	@Autowired
	private UserService userService;

	@Autowired
	private ProfessorService professorService;

	@GetMapping("/")
	public String welcomeMessage()
	{
		return "Welcome to Elearning Management system !!!";
	}

	@PostMapping("/loginuser")
	@CrossOrigin(origins = "http://192.168.65.129:30001")
	public User loginUser(@RequestBody User user) throws Exception
	{
		String currEmail = user.getEmail();
		String currPassword = user.getPassword();

		User userObj = null;
		if(currEmail != null && currPassword != null)
		{
			userObj = userService.fetchUserByEmailAndPassword(currEmail, currPassword);
		}
		if(userObj == null)
		{
			throw new Exception("User does not exists!!! Please enter valid credentials...");
		}
		return userObj;
	}

	@PostMapping("/loginprofessor")
	@CrossOrigin(origins = "http://192.168.65.129:30001")
	public Professor loginDoctor(@RequestBody Professor professor) throws Exception
	{
		String currEmail = professor.getEmail();
		String currPassword = professor.getPassword();

		Professor professorObj = null;
		if(currEmail != null && currPassword != null)
		{
			professorObj = professorService.fetchProfessorByEmailAndPassword(currEmail, currPassword);
		}
		if(professorObj == null)
		{
			throw new Exception("Professor does not exists!!! Please enter valid credentials...");
		}
		return professorObj;
	}
}
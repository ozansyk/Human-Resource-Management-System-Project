package com.ozansyk.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ozansyk.hrms.business.abstracts.EmployerService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {
	
	private EmployerService employerService;

	@Autowired
	public EmployersController(EmployerService employerService) {
		super();
		this.employerService = employerService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Employer>> getAll() {
		return this.employerService.getAll();
	}
	
	@PostMapping("/register")
	public Result add(@RequestParam String email, @RequestParam String password, @RequestParam String passwordConfirmed, 
			@RequestParam String companyName, @RequestParam String webAdress, @RequestParam String phoneNumber) {
		return this.employerService.add(email, password, passwordConfirmed, companyName, webAdress, phoneNumber);
	}
	
	@GetMapping("/getAllActiveJobAdvertisements")
	public DataResult<List<Employer>> getAllActiveJobAdvertisements() {
		return this.employerService.getAllActiveJobAdvertisements();
	}

}

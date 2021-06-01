package com.ozansyk.hrms.api.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ozansyk.hrms.business.abstracts.JobAdvertisemetService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.JobAdvertisement;
import com.ozansyk.hrms.entities.dtos.EmployerWithJobAdvertisementDto;

@RestController
@RequestMapping("/api/jobadvertisements")
public class JobAdvitersementsConttoller {
	
	private JobAdvertisemetService jobAdvertisemetService;

	@Autowired
	public JobAdvitersementsConttoller(JobAdvertisemetService jobAdvertisemetService) {
		this.jobAdvertisemetService = jobAdvertisemetService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getAll(){
		return this.jobAdvertisemetService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestParam String description, @RequestParam int minSalary, @RequestParam int maxSalary, 
			@RequestParam int numberOfPosition, @RequestParam int endTimeYear, @RequestParam int endTimeMonth, 
			@RequestParam int endTimeDay, @RequestParam boolean isActive, @RequestParam String cityName, 
			@RequestParam String jobName, @RequestParam int employerId) {
		return this.jobAdvertisemetService.add(description, minSalary, maxSalary, numberOfPosition, 
				endTimeYear, endTimeMonth, endTimeDay, isActive, cityName, jobName, employerId);
	}
	
	@GetMapping("/getDateDesc")
	public DataResult<List<EmployerWithJobAdvertisementDto>> getActiveJobAdvertisementWithEmployerDetailsSorted() {
		return this.jobAdvertisemetService.getActiveJobAdvertisementWithEmployerDetailsSorted();
	}
	
	@GetMapping("/getJobAdvertisementWithEmployerDetails")
	public DataResult<List<EmployerWithJobAdvertisementDto>> getJobAdvertisementWithEmployerDetails(){
		return this.jobAdvertisemetService.getJobAdvertisementWithEmployerDetails();
	}
	
	@GetMapping("/getActiveJobAdvertisementWithEmployerDetails")
	public DataResult<List<EmployerWithJobAdvertisementDto>> getActiveJobAdvertisementWithEmployerDetails(){
		return this.jobAdvertisemetService.getActiveJobAdvertisementWithEmployerDetails();
	}
	
	@GetMapping("/getAllJobAdvertisementByEmployer")
	public DataResult<List<EmployerWithJobAdvertisementDto>> getAllJobAdvertisementByEmployer(@RequestParam int employerId){
		return this.jobAdvertisemetService.getAllJobAdvertisementByEmployer(employerId);
	}
	
	@PostMapping("/openOrCloseJobAdvertisementById")
	public Result openOrCloseJobAdvertisementById(@RequestParam int jobAdvertisementId, @RequestParam boolean isActive) {
		return this.jobAdvertisemetService.openOrCloseJobAdvertisementById(jobAdvertisementId, isActive);
	}
	
}

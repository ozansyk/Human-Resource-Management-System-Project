package com.ozansyk.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ozansyk.hrms.business.abstracts.JobAdvertisemetService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.JobAdvertisement;
import com.ozansyk.hrms.entities.dtos.EmployerWithJobAdvertisementDto;

@CrossOrigin
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
	public Result add(@RequestBody JobAdvertisement jobAdvertisement) {
		return this.jobAdvertisemetService.add(jobAdvertisement);
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

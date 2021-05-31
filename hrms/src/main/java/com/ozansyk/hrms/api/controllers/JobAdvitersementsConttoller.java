package com.ozansyk.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ozansyk.hrms.business.abstracts.JobAdvertisemetService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.JobAdvertisement;

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
	
	@GetMapping("/getDateAsc")
	public DataResult<List<JobAdvertisement>> getDataAsc() {
		return this.jobAdvertisemetService.getAllSorted();
	}
	
}

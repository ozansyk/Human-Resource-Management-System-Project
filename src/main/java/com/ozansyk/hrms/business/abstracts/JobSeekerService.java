package com.ozansyk.hrms.business.abstracts;

import java.util.List;

import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.JobSeeker;

public interface JobSeekerService {
	
	DataResult<List<JobSeeker>> getAll();
	
	Result add(JobSeeker jobSeeker);

	Result login(JobSeeker jobSeeker);
	
	boolean checkFieldsforRegister(JobSeeker jobSeeker);
	
}

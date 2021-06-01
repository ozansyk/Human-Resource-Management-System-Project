package com.ozansyk.hrms.business.abstracts;

import java.util.List;

import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.JobSeeker;

public interface JobSeekerService {
	
	DataResult<List<JobSeeker>> getAll();
	
	Result add(String firstName, String lastName, String email, String password, String passwordConfirm, String identitynumber, 
		 int birthYear, int birthMonth, int birthDay);
	
	boolean checkFieldsforRegister(JobSeeker jobSeeker);
	
}

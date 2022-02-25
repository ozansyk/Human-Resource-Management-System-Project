package com.ozansyk.hrms.business.abstracts;

import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.Education;

public interface EducationService {
	
	Result addEducationToCv(Education education);
	
	Result deleteEducationFromCv(int jobSeekerId, int educationId);
	
	
	
}

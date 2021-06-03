package com.ozansyk.hrms.business.abstracts;

import com.ozansyk.hrms.core.utilities.results.Result;

public interface EducationService {
	
	Result addEducationToCv(String schoolName, String department, int startTimeYear, int startTimeMonth, int startTimeDay, 
			int graduateTimeYear, int graduateTimeMonth, int graduateTimeDay, boolean isGraduated, int jobSeekerId);
	
	Result deleteEducationFromCv(int jobSeekerId, int educationId);
	
	
	
}

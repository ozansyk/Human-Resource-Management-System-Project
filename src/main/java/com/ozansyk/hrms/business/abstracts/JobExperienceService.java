package com.ozansyk.hrms.business.abstracts;

import com.ozansyk.hrms.core.utilities.results.Result;

public interface JobExperienceService {

	Result addJobExperienceToCv(String workplaceName, String positionName, int startTimeYear, int startTimeMonth, int startTimeDay, 
			int endTimeYear, int endTimeMonth, int endTimeDay, boolean isWorkingNow, int jobSeekerId);
	
	Result deleteJobExperienceFromCv(int jobSeekerId, int jobExperienceId);
	
}

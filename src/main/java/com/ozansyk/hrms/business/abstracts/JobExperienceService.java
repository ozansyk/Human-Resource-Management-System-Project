package com.ozansyk.hrms.business.abstracts;

import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.JobExperience;

public interface JobExperienceService {

	Result addJobExperienceToCv(JobExperience jobExperience);
	
	Result deleteJobExperienceFromCv(int jobSeekerId, int jobExperienceId);
	
}

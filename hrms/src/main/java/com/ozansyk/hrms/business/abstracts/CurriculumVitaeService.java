package com.ozansyk.hrms.business.abstracts;

import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.CurriculumVitae;

public interface CurriculumVitaeService {
	Result addCv(int jobSeekerId, String githubAddress, String linkedinAddress);
	
	DataResult<CurriculumVitae> getCv(int jobSeekerId);
	
	Result addEducationToCv(String schoolName, String department, int startTimeYear, int startTimeMonth, int startTimeDay, 
			int graduateTimeYear, int graduateTimeMonth, int graduateTimeDay, boolean isGraduated, int jobSeekerId);
	
	Result addJobExperienceToCv(String workplaceName, String positionName, int startTimeYear, int startTimeMonth, int startTimeDay, 
			int endTimeYear, int endTimeMonth, int endTimeDay, boolean isWorkingNow, int jobSeekerId);
	
	Result addLanguageToCv(String languageName, int languageLevel, int jobSeekerId);
	
	Result addProgramingLanguageToCv(String programingLanguageName, int jobSeekerId);
	
	Result addCoverLetterToCv(String letter, int jobSeekerId);
	
}

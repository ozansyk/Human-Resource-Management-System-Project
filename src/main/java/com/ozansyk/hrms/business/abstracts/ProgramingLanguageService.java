package com.ozansyk.hrms.business.abstracts;

import com.ozansyk.hrms.core.utilities.results.Result;

public interface ProgramingLanguageService {
	
	Result addProgramingLanguageToCv(String programingLanguageName, int jobSeekerId);
	
	Result deleteProgramingLanguageFromCv(int jobSeekerId, int programingLanguageId);
	
}

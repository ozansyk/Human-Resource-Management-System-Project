package com.ozansyk.hrms.business.abstracts;

import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.ProgramingLanguage;

public interface ProgramingLanguageService {
	
	Result addProgramingLanguageToCv(ProgramingLanguage programingLanguage);
	
	Result deleteProgramingLanguageFromCv(int jobSeekerId, int programingLanguageId);
	
}

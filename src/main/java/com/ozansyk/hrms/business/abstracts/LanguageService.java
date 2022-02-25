package com.ozansyk.hrms.business.abstracts;

import com.ozansyk.hrms.core.utilities.results.Result;

public interface LanguageService {
	
	Result addLanguageToCv(String languageName, int languageLevel, int jobSeekerId);
	
	Result deleteLanguageFromCv(int jobSeekerId, int languageId);
	
}

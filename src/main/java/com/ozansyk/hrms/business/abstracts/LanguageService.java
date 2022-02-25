package com.ozansyk.hrms.business.abstracts;

import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.Language;

public interface LanguageService {
	
	Result addLanguageToCv(Language language);
	
	Result deleteLanguageFromCv(int jobSeekerId, int languageId);
	
}

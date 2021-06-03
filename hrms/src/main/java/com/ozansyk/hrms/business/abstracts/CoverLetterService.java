package com.ozansyk.hrms.business.abstracts;

import com.ozansyk.hrms.core.utilities.results.Result;

public interface CoverLetterService {

	Result addCoverLetterToCv(String letter, int jobSeekerId);
	
	Result deleteCoverLetterFromCv(int jobSeekerId, int coverLetterId);
	
}

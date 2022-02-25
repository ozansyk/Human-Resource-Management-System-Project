package com.ozansyk.hrms.business.abstracts;

import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.CoverLetter;

public interface CoverLetterService {

	Result addCoverLetterToCv(CoverLetter coverLetter);
	
	Result deleteCoverLetterFromCv(int jobSeekerId, int coverLetterId);
	
}

package com.ozansyk.hrms.business.abstracts;

import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.CurriculumVitae;

public interface CurriculumVitaeService {
	Result addCv(int jobSeekerId, String githubAddress, String linkedinAddress);
	
	DataResult<CurriculumVitae> getCv(int jobSeekerId);
	
	Result updateCv(int jobSeekerId, String githubAddress, String linkedinAddress);
	
}

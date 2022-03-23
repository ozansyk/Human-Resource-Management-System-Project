package com.ozansyk.hrms.business.abstracts;

import java.util.List;

import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.dtos.responseDtos.CurriculumVitaeDtoResponse;

public interface CurriculumVitaeService {
	Result addCv(CurriculumVitae curriculumVitae);
	
	DataResult<CurriculumVitaeDtoResponse> getCv(int jobSeekerId);
	
	Result updateCv(CurriculumVitae curriculumVitae);
	
	DataResult<List<CurriculumVitae>> getAllCv();

    Result deleteCv(int id);
}

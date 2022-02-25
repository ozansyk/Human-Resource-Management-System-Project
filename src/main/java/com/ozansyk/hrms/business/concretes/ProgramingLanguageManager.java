package com.ozansyk.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.ProgramingLanguageService;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import com.ozansyk.hrms.dataAccess.abstracts.JobSeekerDao;
import com.ozansyk.hrms.dataAccess.abstracts.ProgramingLanguageDao;
import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.concretes.JobExperience;
import com.ozansyk.hrms.entities.concretes.JobSeeker;
import com.ozansyk.hrms.entities.concretes.ProgramingLanguage;

@Service
public class ProgramingLanguageManager implements ProgramingLanguageService {

	private ProgramingLanguageDao programingLanguageDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	private JobSeekerDao jobSeekerDao;
	
	@Autowired
	public ProgramingLanguageManager(ProgramingLanguageDao programingLanguageDao, CurriculumVitaeDao curriculumVitaeDao,
			JobSeekerDao jobSeekerDao) {
		this.programingLanguageDao = programingLanguageDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.jobSeekerDao = jobSeekerDao;
	}

	@Override
	public Result addProgramingLanguageToCv(ProgramingLanguage programingLanguage) {

		this.programingLanguageDao.save(programingLanguage);
		return new SuccessResult("Program/Teknoloji bilgisi eklendi.");

	}

	@Override
	public Result deleteProgramingLanguageFromCv(int jobSeekerId, int programingLanguageId) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		
		for(ProgramingLanguage p : jobSeeker.getCurriculumVitae().getProgramingLanguages()) {
			if(p.getId() == programingLanguageId) {
				this.programingLanguageDao.deleteById(programingLanguageId);
		return new SuccessResult("Programlama dili/Teknoloji bilgisi silindi.");
			}
		}
		return new ErrorResult("Programlama dili/Teknoloji bilgisi bulunamadı.");
		
	}
	
}

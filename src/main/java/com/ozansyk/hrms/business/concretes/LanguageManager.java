package com.ozansyk.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.LanguageService;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import com.ozansyk.hrms.dataAccess.abstracts.JobSeekerDao;
import com.ozansyk.hrms.dataAccess.abstracts.LanguageDao;
import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.concretes.JobExperience;
import com.ozansyk.hrms.entities.concretes.JobSeeker;
import com.ozansyk.hrms.entities.concretes.Language;

@Service
public class LanguageManager implements LanguageService {

	private LanguageDao languageDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	private JobSeekerDao jobSeekerDao;
	
	@Autowired
	public LanguageManager(LanguageDao languageDao, CurriculumVitaeDao curriculumVitaeDao, JobSeekerDao jobSeekerDao) {
		this.languageDao = languageDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.jobSeekerDao = jobSeekerDao;
	}

	@Override
	public Result addLanguageToCv(String languageName, int languageLevel, int jobSeekerId) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		CurriculumVitae curriculumVitae = this.curriculumVitaeDao.getByJobSeeker(jobSeeker);
		
		Language language = new Language(languageName, languageLevel, curriculumVitae);
		this.languageDao.save(language);
		
		return new SuccessResult("Yabancı dil bilgisi eklendi.");
		
	}

	@Override
	public Result deleteLanguageFromCv(int jobSeekerId, int languageId) {
		
JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		
		for(Language l : jobSeeker.getCurriculumVitae().getLanguages()) {
			if(l.getId() == languageId) {
				this.languageDao.deleteById(languageId);
		return new SuccessResult("Yabancı dil bilgisi silindi.");
			}
		}
		return new ErrorResult("Yabancı dil bilgisi bulunamadı.");
		
	}
	
}

package com.ozansyk.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.CurriculumVitaeService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessDataResult;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.dataAccess.abstracts.CoverLetterDao;
import com.ozansyk.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import com.ozansyk.hrms.dataAccess.abstracts.EducationDao;
import com.ozansyk.hrms.dataAccess.abstracts.JobExperienceDao;
import com.ozansyk.hrms.dataAccess.abstracts.JobSeekerDao;
import com.ozansyk.hrms.dataAccess.abstracts.LanguageDao;
import com.ozansyk.hrms.dataAccess.abstracts.ProgramingLanguageDao;
import com.ozansyk.hrms.entities.concretes.CoverLetter;
import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.concretes.Education;
import com.ozansyk.hrms.entities.concretes.JobExperience;
import com.ozansyk.hrms.entities.concretes.JobSeeker;
import com.ozansyk.hrms.entities.concretes.Language;
import com.ozansyk.hrms.entities.concretes.ProgramingLanguage;

@Service
public class CurriculumVitaeManager implements CurriculumVitaeService {

	private CurriculumVitaeDao curriculumVitaeDao;
	private JobSeekerDao jobSeekerDao;
	
	@Autowired
	public CurriculumVitaeManager(CurriculumVitaeDao curriculumVitaeDao, JobSeekerDao jobSeekerDao) {
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.jobSeekerDao = jobSeekerDao;
	}

	//Get Cv By Id
	@Override
	public DataResult<CurriculumVitae> getCv(int jobSeekerId) {
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		return new SuccessDataResult<CurriculumVitae>(this.curriculumVitaeDao.getByJobSeeker(jobSeeker), "Cv başarıyla getirildi.");
	}
	
	//Create Cv
	@Override
	public Result addCv(int jobSeekerId, String githubAddress, String linkedinAddress) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		if(jobSeeker.getCurriculumVitae() != null) {
			return new ErrorResult("Cv'niz zaten mevcut. Önceki cv'nizi silmeniz ya da güncellemeniz gerekmektedir.");
		}
		CurriculumVitae curriculumVitae = new CurriculumVitae(jobSeeker, githubAddress, linkedinAddress);
		this.curriculumVitaeDao.save(curriculumVitae);
		return new SuccessResult("Cv başarıyla oluşturuldu. Diğer alanları doldurabilirsiniz.");
	}

	//Update Cv
	@Override
	public Result updateCv(int jobSeekerId, String githubAddress, String linkedinAddress) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		jobSeeker.setCurriculumVitae(new CurriculumVitae(jobSeeker, githubAddress, linkedinAddress));
		this.jobSeekerDao.save(jobSeeker);
		
		return new SuccessResult("Cv'niz başarıyla güncellendi.");
	}

	@Override
	public DataResult<List<CurriculumVitae>> getAllCv() {
		return new SuccessDataResult<List<CurriculumVitae>>(this.curriculumVitaeDao.findAll());
	}

	
	
}

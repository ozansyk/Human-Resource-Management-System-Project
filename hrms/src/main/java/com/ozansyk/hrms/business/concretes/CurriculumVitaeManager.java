package com.ozansyk.hrms.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.CurriculumVitaeService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
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
	private EducationDao educationDao;
	private JobExperienceDao jobExperinceDao;
	private LanguageDao languageDao;
	private ProgramingLanguageDao programingLanguageDao;
	private CoverLetterDao coverLetterDao;
	
	@Autowired
	public CurriculumVitaeManager(CurriculumVitaeDao curriculumVitaeDao, JobSeekerDao jobSeekerDao, EducationDao educationDao, 
			JobExperienceDao jobExperinceDao, LanguageDao languageDao, ProgramingLanguageDao programingLanguageDao, CoverLetterDao coverLetterDao) {
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.jobSeekerDao = jobSeekerDao;
		this.educationDao = educationDao;
		this.jobExperinceDao = jobExperinceDao;
		this.languageDao = languageDao;
		this.programingLanguageDao = programingLanguageDao;
		this.coverLetterDao = coverLetterDao;
	}

	//Get Cv
	@Override
	public DataResult<CurriculumVitae> getCv(int jobSeekerId) {
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		return new SuccessDataResult<CurriculumVitae>(this.curriculumVitaeDao.getByJobSeeker(jobSeeker), "Cv başarıyla getirildi.");
	}
	
	//Add to Cv Methods
	@Override
	public Result addCv(int jobSeekerId, String githubAddress, String linkedinAddress) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		CurriculumVitae curriculumVitae = new CurriculumVitae(jobSeeker, githubAddress, linkedinAddress);
		this.curriculumVitaeDao.save(curriculumVitae);
		return new SuccessResult("Cv başarıyla oluşturuldu. Diğer alanları doldurabilirsiniz.");
	}

	@Override
	public Result addEducationToCv(String schoolName, String department, int startTimeYear, int startTimeMonth,
			int startTimeDay, int graduateTimeYear, int graduateTimeMonth, int graduateTimeDay, boolean isGraduated,
			int jobSeekerId) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		CurriculumVitae curriculumVitae = this.curriculumVitaeDao.getByJobSeeker(jobSeeker);
		
		LocalDate startDate = LocalDate.of(startTimeYear, startTimeMonth, startTimeDay);
		LocalDate graduationDate = null;
		
		if(isGraduated == true) {
			graduationDate = LocalDate.of(graduateTimeYear, graduateTimeMonth, graduateTimeDay);
			Education education = new Education(schoolName, department, startDate, graduationDate, isGraduated, curriculumVitae);
			this.educationDao.save(education);
		} else {
			Education education = new Education(schoolName, department, startDate, isGraduated, curriculumVitae);
			this.educationDao.save(education);
		}
		
		return new SuccessResult("Eğitim bilgisi başarıyla eklendi.");
	}

	@Override
	public Result addJobExperienceToCv(String workplaceName, String positionName, int startTimeYear, int startTimeMonth,
			int startTimeDay, int endTimeYear, int endTimeMonth, int endTimeDay, boolean isWorkingNow,
			int jobSeekerId) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		CurriculumVitae curriculumVitae = this.curriculumVitaeDao.getByJobSeeker(jobSeeker);
		
		LocalDate startDate = LocalDate.of(startTimeYear, startTimeMonth, startTimeDay);
		LocalDate endDate = null;
		
		if(isWorkingNow == false) {
			endDate = LocalDate.of(endTimeYear, endTimeMonth, endTimeDay);
			JobExperience jobExperience = new JobExperience(workplaceName, positionName, startDate, endDate, isWorkingNow, curriculumVitae);
			this.jobExperinceDao.save(jobExperience);
		} else {
			JobExperience jobExperience = new JobExperience(workplaceName, positionName, startDate, isWorkingNow, curriculumVitae);
			this.jobExperinceDao.save(jobExperience);
		}
		
		return new SuccessResult("İş tecrübesi başarıyla kaydedildi.");
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
	public Result addProgramingLanguageToCv(String programingLanguageName, int jobSeekerId) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		CurriculumVitae curriculumVitae = this.curriculumVitaeDao.getByJobSeeker(jobSeeker);
		
		ProgramingLanguage programingLanguage = new ProgramingLanguage(programingLanguageName, curriculumVitae);
		this.programingLanguageDao.save(programingLanguage);
		return new SuccessResult("Program/Teknoloji bilgisi eklendi.");
	}

	@Override
	public Result addCoverLetterToCv(String letter, int jobSeekerId) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		CurriculumVitae curriculumVitae = this.curriculumVitaeDao.getByJobSeeker(jobSeeker);
		
		CoverLetter coverLetter = new CoverLetter(letter, curriculumVitae);
		this.coverLetterDao.save(coverLetter);
		
		return new SuccessResult("Önyazı başarıyla eklendi.");
	}
	
}

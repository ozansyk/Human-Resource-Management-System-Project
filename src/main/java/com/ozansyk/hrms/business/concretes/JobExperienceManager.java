package com.ozansyk.hrms.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.JobExperienceService;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import com.ozansyk.hrms.dataAccess.abstracts.EducationDao;
import com.ozansyk.hrms.dataAccess.abstracts.JobExperienceDao;
import com.ozansyk.hrms.dataAccess.abstracts.JobSeekerDao;
import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.concretes.Education;
import com.ozansyk.hrms.entities.concretes.JobExperience;
import com.ozansyk.hrms.entities.concretes.JobSeeker;

@Service
public class JobExperienceManager implements JobExperienceService {

	private JobExperienceDao jobExperinceDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	private JobSeekerDao jobSeekerDao;
	
	@Autowired
	public JobExperienceManager(JobExperienceDao jobExperinceDao, CurriculumVitaeDao curriculumVitaeDao, JobSeekerDao jobSeekerDao) {
		this.jobExperinceDao = jobExperinceDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.jobSeekerDao = jobSeekerDao;
	}

	@Override
	public Result addJobExperienceToCv(JobExperience jobExperience) {
		
		if(!jobExperience.isWorkingNow()) {
			this.jobExperinceDao.save(jobExperience);
		} else {
			jobExperience.setEndDate(null);
			this.jobExperinceDao.save(jobExperience);
		}
		
		return new SuccessResult("İş tecrübesi başarıyla kaydedildi.");
		
	}

	@Override
	public Result deleteJobExperienceFromCv(int jobSeekerId, int jobExperienceId) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		
		for(JobExperience j : jobSeeker.getCurriculumVitae().getJobExperience()) {
			if(j.getId() == jobExperienceId) {
				this.jobExperinceDao.deleteById(jobExperienceId);
		return new SuccessResult("İş deneyimi bilgisi silindi.");
			}
		}
		return new ErrorResult("İş deneyimi bilgisi bulunamadı.");
		
	}
	
}

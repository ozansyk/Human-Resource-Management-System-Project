package com.ozansyk.hrms.business.concretes;

import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.EducationService;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import com.ozansyk.hrms.dataAccess.abstracts.EducationDao;
import com.ozansyk.hrms.dataAccess.abstracts.JobSeekerDao;
import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.concretes.Education;
import com.ozansyk.hrms.entities.concretes.JobSeeker;

@Service
public class EducationManager implements EducationService {
	
	private EducationDao educationDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	private JobSeekerDao jobSeekerDao;

	@Autowired
	public EducationManager(EducationDao educationDao, CurriculumVitaeDao curriculumVitaeDao,
			JobSeekerDao jobSeekerDao) {
		this.educationDao = educationDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.jobSeekerDao = jobSeekerDao;
	}

	@Override
	public Result addEducationToCv(Education education) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(education));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		educationDao.save(education);
		return new SuccessResult("Eğitim bilgisi başarıyla eklendi");
	}

	@Override
	public Result deleteEducationFromCv(int jobSeekerId, int educationId) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		
		for(Education e : jobSeeker.getCurriculumVitae().getEducations()) {
			if(e.getId() == educationId) {
				this.educationDao.deleteById(educationId);
		return new SuccessResult("Eğitim bilgisi silindi.");
			}
		}
		return new ErrorResult("Eğitim bilgisi bulunamadı.");
		
	}
	
}

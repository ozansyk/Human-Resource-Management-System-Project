package com.ozansyk.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.CoverLetterService;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.dataAccess.abstracts.CoverLetterDao;
import com.ozansyk.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import com.ozansyk.hrms.dataAccess.abstracts.JobSeekerDao;
import com.ozansyk.hrms.entities.concretes.CoverLetter;
import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.concretes.JobExperience;
import com.ozansyk.hrms.entities.concretes.JobSeeker;

@Service
public class CoverLetterManager implements CoverLetterService {

	private CoverLetterDao coverLetterDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	private JobSeekerDao jobSeekerDao;
	
	@Autowired
	public CoverLetterManager(CoverLetterDao coverLetterDao, CurriculumVitaeDao curriculumVitaeDao,
			JobSeekerDao jobSeekerDao) {
		this.coverLetterDao = coverLetterDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.jobSeekerDao = jobSeekerDao;
	}

	@Override
	public Result addCoverLetterToCv(String letter, int jobSeekerId) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		CurriculumVitae curriculumVitae = this.curriculumVitaeDao.getByJobSeeker(jobSeeker);
		
		CoverLetter coverLetter = new CoverLetter(letter, curriculumVitae);
		this.coverLetterDao.save(coverLetter);
		
		return new SuccessResult("Önyazı başarıyla eklendi.");
		
	}

	@Override
	public Result deleteCoverLetterFromCv(int jobSeekerId, int coverLetterId) {
		
JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		
		for(CoverLetter c : jobSeeker.getCurriculumVitae().getCoverLetters()) {
			if(c.getId() == coverLetterId) {
				this.coverLetterDao.deleteById(coverLetterId);
		return new SuccessResult("Önyazı silindi.");
			}
		}
		return new ErrorResult("Silinecek önyazı bulunamadı.");
		
	}
	
}

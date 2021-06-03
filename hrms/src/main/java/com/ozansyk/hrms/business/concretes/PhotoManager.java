package com.ozansyk.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.PhotoService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessDataResult;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import com.ozansyk.hrms.dataAccess.abstracts.JobSeekerDao;
import com.ozansyk.hrms.dataAccess.abstracts.PhotoDao;
import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.concretes.JobSeeker;
import com.ozansyk.hrms.entities.concretes.Photo;

@Service
public class PhotoManager implements PhotoService {
	
	private PhotoDao photoDao;
	private JobSeekerDao jobSeekerDao;
	private CurriculumVitaeDao curriculumVitaeDao;

	@Autowired
	public PhotoManager(PhotoDao photoDao, JobSeekerDao jobSeekerDao, CurriculumVitaeDao curriculumVitaeDao) {
		this.photoDao = photoDao;
		this.jobSeekerDao = jobSeekerDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
	}

	@Override
	public DataResult<List<Photo>> getAllPhotos() {
		return new SuccessDataResult<List<Photo>>(this.photoDao.findAll());
	}

	@Override
	public Result add(Photo photo, int jobSeekerId) {
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		photo.setJobSeeker(jobSeeker);
		CurriculumVitae curriculumVitae = this.curriculumVitaeDao.getByJobSeeker(jobSeeker);
		photo.setCurriculumVitae(curriculumVitae);
		this.photoDao.save(photo);
		return new SuccessResult("Foto yüklendi.");
	}

	@Override
	public Result delete(int id) {
		this.photoDao.deleteById(id);
		return new SuccessResult("Foto başarıyla silindi.");
	}

	@Override
	public DataResult<Photo> getById(int id) {
		return new SuccessDataResult<Photo>(this.photoDao.getById(id), "Foto bulundu.");
	}

	@Override
	public Result deletePhotoFromCv(int jobSeekerId, int photoId) {
		
		JobSeeker jobSeeker = this.jobSeekerDao.getById(jobSeekerId);
		for(Photo p : jobSeeker.getCurriculumVitae().getPhotos()) {
			if(p.getId() == photoId) {
				this.photoDao.deleteById(photoId);
				return new SuccessResult("Foto Cv'den başarıyla silindi.");
			}
		}
		
		return new ErrorResult("Cv'de silinecek foto bulunamadı.");
		
	}
	
}

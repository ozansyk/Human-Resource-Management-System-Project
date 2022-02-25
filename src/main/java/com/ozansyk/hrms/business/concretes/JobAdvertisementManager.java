package com.ozansyk.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.JobAdvertisemetService;
import com.ozansyk.hrms.business.constants.Messages;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessDataResult;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.dataAccess.abstracts.CityDao;
import com.ozansyk.hrms.dataAccess.abstracts.EmployerDao;
import com.ozansyk.hrms.dataAccess.abstracts.JobAdvertisementDao;
import com.ozansyk.hrms.dataAccess.abstracts.JobDao;
import com.ozansyk.hrms.entities.concretes.City;
import com.ozansyk.hrms.entities.concretes.Employer;
import com.ozansyk.hrms.entities.concretes.Job;
import com.ozansyk.hrms.entities.concretes.JobAdvertisement;
import com.ozansyk.hrms.entities.dtos.EmployerWithJobAdvertisementDto;

@Service
public class JobAdvertisementManager implements JobAdvertisemetService {
	
	private JobAdvertisementDao jobAdvertisementDao;
	private CityDao cityDao;
	private JobDao jobDao;
	private EmployerDao employerDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, CityDao cityDao, JobDao jobDao, EmployerDao employerDao) {
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.cityDao = cityDao;
		this.jobDao = jobDao;
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>> (this.jobAdvertisementDao.findAll(), Messages.jobAdvertisementListed);
	}

	@Override
	public Result add(String description, int minSalary, int maxSalary, int numberOfPosition, 
			int endTimeYear, int endTimeMonth, int endTimeDay, boolean isActive, String cityName, String jobName, int employerId) {
		
		City city = this.cityDao.getByCityName(cityName);
		Job job = this.jobDao.getByJobName(jobName);
		Employer employer = this.employerDao.getById(employerId);
		
		LocalDate publishDate = LocalDate.now();
		LocalDate endTime = LocalDate.of(endTimeYear, endTimeMonth, endTimeDay);
		
		JobAdvertisement jobAdvertisement = new JobAdvertisement(description, minSalary, maxSalary, numberOfPosition, publishDate, 
				endTime, isActive, city, job, employer);
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult(Messages.jobAdvertisementSuccessAdd);
	}

	@Override
	public DataResult<List<EmployerWithJobAdvertisementDto>> getActiveJobAdvertisementWithEmployerDetailsSorted() {
		Sort sort = Sort.by(Sort.Direction.DESC, "publishDate");
		return new SuccessDataResult<List<EmployerWithJobAdvertisementDto>>(this.jobAdvertisementDao.getActiveJobAdvertisementWithEmployerDetails(sort));
	}

	@Override
	public DataResult<List<EmployerWithJobAdvertisementDto>> getJobAdvertisementWithEmployerDetails() {
		return new SuccessDataResult<List<EmployerWithJobAdvertisementDto>>(this.jobAdvertisementDao.getJobAdvertisementWithEmployerDetails(),
				"Data başarıyla getirildi.");
	}

	@Override
	public DataResult<List<EmployerWithJobAdvertisementDto>> getActiveJobAdvertisementWithEmployerDetails() {
		return new SuccessDataResult<List<EmployerWithJobAdvertisementDto>>(this.jobAdvertisementDao.getActiveJobAdvertisementWithEmployerDetails(),
				"Data başarıyla getirildi.");
	}

	@Override
	public DataResult<List<EmployerWithJobAdvertisementDto>> getAllJobAdvertisementByEmployer(int employerId) {
		return new SuccessDataResult<List<EmployerWithJobAdvertisementDto>>(this.jobAdvertisementDao.getAllJobAdvertisementByEmployer(employerId));
	}

	@Override
	public Result openOrCloseJobAdvertisementById(int jobAdvertisementId, boolean isActive) {
		JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.getById(jobAdvertisementId);
		jobAdvertisement.setActive(isActive);
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("İlanın aktiflik durumu " + jobAdvertisement.isActive() + " olarak değiştirildi.");
	}
	
	
}

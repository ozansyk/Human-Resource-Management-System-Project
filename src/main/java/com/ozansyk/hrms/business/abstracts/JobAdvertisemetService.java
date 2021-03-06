package com.ozansyk.hrms.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.JobAdvertisement;
import com.ozansyk.hrms.entities.dtos.EmployerWithJobAdvertisementDto;

public interface JobAdvertisemetService {
	DataResult<List<JobAdvertisement>> getAll();
	Result add(JobAdvertisement jobAdvertisement);
	
	DataResult<List<EmployerWithJobAdvertisementDto>> getJobAdvertisementWithEmployerDetails();
	DataResult<List<EmployerWithJobAdvertisementDto>> getActiveJobAdvertisementWithEmployerDetails();
	DataResult<List<EmployerWithJobAdvertisementDto>> getActiveJobAdvertisementWithEmployerDetailsSorted();
	
	DataResult<List<EmployerWithJobAdvertisementDto>> getAllJobAdvertisementByEmployer(int employerId);
	
	Result openOrCloseJobAdvertisementById(int jobAdvertisementId, boolean isActive);
}

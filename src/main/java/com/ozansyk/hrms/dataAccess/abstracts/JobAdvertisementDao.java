package com.ozansyk.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ozansyk.hrms.entities.concretes.JobAdvertisement;
import com.ozansyk.hrms.entities.dtos.EmployerWithJobAdvertisementDto;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {
	
	@Query("Select new com.ozansyk.hrms.entities.dtos.EmployerWithJobAdvertisementDto"
			+ "(j.id, e.companyName, j.job.jobName, j.numberOfPosition, j.publishDate, j.endTime, j.isActive) "
			+ "From JobAdvertisement j Inner Join j.employer e")
	List<EmployerWithJobAdvertisementDto> getJobAdvertisementWithEmployerDetails();
	
	@Query("Select new com.ozansyk.hrms.entities.dtos.EmployerWithJobAdvertisementDto"
			+ "(j.id, e.companyName, j.job.jobName, j.numberOfPosition, j.publishDate, j.endTime, j.isActive) "
			+ "From JobAdvertisement j Inner Join j.employer e Where j.isActive=true")
	List<EmployerWithJobAdvertisementDto> getActiveJobAdvertisementWithEmployerDetails();
	
	@Query("Select new com.ozansyk.hrms.entities.dtos.EmployerWithJobAdvertisementDto"
			+ "(j.id, e.companyName, j.job.jobName, j.numberOfPosition, j.publishDate, j.endTime, j.isActive) "
			+ "From JobAdvertisement j Inner Join j.employer e Where j.isActive=true")
	List<EmployerWithJobAdvertisementDto> getActiveJobAdvertisementWithEmployerDetails(Sort sort);
	
	@Query("Select new com.ozansyk.hrms.entities.dtos.EmployerWithJobAdvertisementDto"
			+ "(j.id, e.companyName, j.job.jobName, j.numberOfPosition, j.publishDate, j.endTime, j.isActive) "
			+ "From JobAdvertisement j Inner Join j.employer e Where j.isActive=true AND e.id=:employerId")
	List<EmployerWithJobAdvertisementDto> getAllJobAdvertisementByEmployer(int employerId);
	
	JobAdvertisement getById(int jobAdvertisementId);
}

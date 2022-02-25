package com.ozansyk.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ozansyk.hrms.entities.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer> {
	
	@Query("From Employer where isActivated=true")
	List<Employer> getAllActiveJobAdvertisements();
	
	Employer getById(int id);
	
}

package com.ozansyk.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.concretes.JobSeeker;

public interface CurriculumVitaeDao extends JpaRepository<CurriculumVitae, Integer> {
	CurriculumVitae getByJobSeeker(JobSeeker jobSeeker);
}

package com.ozansyk.hrms.dataAccess.abstracts;

import com.ozansyk.hrms.entities.dtos.responseDtos.CurriculumVitaeDtoResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumVitaeDao extends JpaRepository<CurriculumVitae, Integer> {
	@Query("SELECT new com.ozansyk.hrms.entities.dtos.responseDtos.CurriculumVitaeDtoResponse" +
			"(c.id, c.githubAddress, c.linkedinAddress, c.educations, c.jobExperience, c.languages, c.photos, c.programingLanguages, c.coverLetters) " +
			"FROM CurriculumVitae c WHERE c.jobSeeker =: jobSeeker")
	CurriculumVitaeDtoResponse getByJobSeeker2(JobSeeker jobSeeker);

	CurriculumVitae getByJobSeeker(JobSeeker jobSeeker);
}

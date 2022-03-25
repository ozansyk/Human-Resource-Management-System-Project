package com.ozansyk.hrms.dataAccess.abstracts;

import com.ozansyk.hrms.entities.dtos.responseDtos.CurriculumVitaeDtoResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumVitaeDao extends JpaRepository<CurriculumVitae, Integer> {
	@Query(value = "SELECT new com.ozansyk.hrms.entities.dtos.responseDtos.CurriculumVitaeDtoResponse" +
			"(c.id, c.githubAddress, c.linkedinAddress, c.educations, c.jobExperience, c.languages, c.photos, c.programingLanguages, c.coverLetters) " +
			"FROM CurriculumVitae c JOIN Education e ON c.id = e.curriculumVitae.id JOIN JobExperience j ON c.id = j.curriculumVitae.id " +
			"JOIN Language l ON c.id = l.curriculumVitae.id JOIN Photo p ON c.id = p.curriculumVitae.id JOIN ProgramingLanguage p2 ON c.id = p2.curriculumVitae.id " +
			"JOIN CoverLetter c2 ON c.id = c2.curriculumVitae.id WHERE c.jobSeeker =: jobSeeker", nativeQuery = true)
	CurriculumVitaeDtoResponse getByJobSeeker2(JobSeeker jobSeeker);

	CurriculumVitae getByJobSeeker(JobSeeker jobSeeker);
}


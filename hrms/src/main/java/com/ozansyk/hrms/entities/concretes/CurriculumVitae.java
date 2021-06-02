package com.ozansyk.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="curriculum_vitaes")
public class CurriculumVitae {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@OneToOne
	@JoinColumn(name="job_seeker_id")
	private JobSeeker jobSeeker;
	
	@Column(name="github_address")
	private String githubAddress;
	
	@Column(name="linkedin_address")
	private String linkedinAddress;
	
	
	@OneToMany(mappedBy = "curriculumVitae")
	private List<Education> educations;
	
	@OneToMany(mappedBy = "curriculumVitae")
	private List<JobExperience> jobExperience;
	
	@OneToMany(mappedBy = "curriculumVitae")
	private List<Language> languages;
	
	@OneToMany(mappedBy = "curriculumVitae")
	private List<Photo> photos;
	
	@OneToMany(mappedBy = "curriculumVitae")
	private List<ProgramingLanguage> programingLanguages;
	
	@OneToMany(mappedBy = "curriculumVitae")
	private List<CoverLetter> coverLetters;

	public CurriculumVitae(JobSeeker jobSeeker, String githubAddress, String linkedinAddress) {
		this.jobSeeker = jobSeeker;
		this.githubAddress = githubAddress;
		this.linkedinAddress = linkedinAddress;
	}
}

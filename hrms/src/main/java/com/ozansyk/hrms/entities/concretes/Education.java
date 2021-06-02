package com.ozansyk.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","curriculumVitae"})
@Table(name="educations")
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="school_name")
	private String schoolName;
	
	@Column(name="department")
	private String department;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="graduation_date")
	private LocalDate graduationDate;
	
	@Column(name="is_graduated")
	private boolean isGraduated;
	
	@ManyToOne
	@JoinColumn(name="curriculum_vitae_id")
	private CurriculumVitae curriculumVitae;

	public Education(String schoolName, String department, LocalDate startDate, LocalDate graduationDate,
			boolean isGraduated, CurriculumVitae curriculumVitae) {
		this.schoolName = schoolName;
		this.department = department;
		this.startDate = startDate;
		this.graduationDate = graduationDate;
		this.isGraduated = isGraduated;
		this.curriculumVitae = curriculumVitae;
	}
	
	public Education(String schoolName, String department, LocalDate startDate,
			boolean isGraduated, CurriculumVitae curriculumVitae) {
		this.schoolName = schoolName;
		this.department = department;
		this.startDate = startDate;
		this.isGraduated = isGraduated;
		this.curriculumVitae = curriculumVitae;
	}
}

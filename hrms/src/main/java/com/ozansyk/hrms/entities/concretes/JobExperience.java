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
@Table(name="job_experiences")
public class JobExperience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="workplace_name")
	private String workplaceName;
	
	@Column(name="position_name")
	private String positionName;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="is_working_now")
	private boolean isWorkingNow;
	
	@ManyToOne
	@JoinColumn(name="curriculum_vitae_id")
	private CurriculumVitae curriculumVitae;

	public JobExperience(String workplaceName, String positionName, LocalDate startDate, LocalDate endDate,
			boolean isWorkingNow, CurriculumVitae curriculumVitae) {
		this.workplaceName = workplaceName;
		this.positionName = positionName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isWorkingNow = isWorkingNow;
		this.curriculumVitae = curriculumVitae;
	}
	
	public JobExperience(String workplaceName, String positionName, LocalDate startDate,
			boolean isWorkingNow, CurriculumVitae curriculumVitae) {
		this.workplaceName = workplaceName;
		this.positionName = positionName;
		this.startDate = startDate;
		this.isWorkingNow = isWorkingNow;
		this.curriculumVitae = curriculumVitae;
	}
}

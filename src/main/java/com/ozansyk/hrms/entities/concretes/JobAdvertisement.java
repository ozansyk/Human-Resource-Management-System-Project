package com.ozansyk.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="job_advertisement")
@NoArgsConstructor
public class JobAdvertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="min_salary")
	private int minSalary;
	
	@Column(name="max_salary")
	private int maxSalary;
	
	@Column(name="number_positions")
	private int numberOfPosition;
	
	@Column(name="publish_date")
	private LocalDate publishDate;
	
	@Column(name="end_time")
	private LocalDate endTime;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@ManyToOne()
	@JoinColumn(name="city_id")
	private City city;
	
	@ManyToOne()
	@JoinColumn(name="job_id")
	private Job job;
	
	@ManyToOne()
	@JoinColumn(name="employer_id")
	private Employer employer;

	public JobAdvertisement(String description, int minSalary, int maxSalary, int numberOfPosition, LocalDate publishDate,
			LocalDate endTime, boolean isActive, City city, Job job, Employer employer) {
		this.description = description;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.numberOfPosition = numberOfPosition;
		this.publishDate = publishDate;
		this.endTime = endTime;
		this.isActive = isActive;
		this.city = city;
		this.job = job;
		this.employer = employer;
	}
	
}

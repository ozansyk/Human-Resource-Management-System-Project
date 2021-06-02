package com.ozansyk.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","curriculumVitae"})
@Table(name="photos")
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="photo_name")
	private String photoName;
	
	@Column(name="photo_url")
	private String photoUrl;
	
	@Column(name="photo_id")
	private String photoId;
	
	@ManyToOne()
	@JoinColumn(name="job_seeker_id")
	private JobSeeker jobSeeker;
	
	@ManyToOne
	@JoinColumn(name="curriculum_vitae_id")
	private CurriculumVitae curriculumVitae;

	public Photo(String photoName, String photoUrl, String photoId) {
		this.photoName = photoName;
		this.photoUrl = photoUrl;
		this.photoId = photoId;
	}

	public Photo(String photoName, String photoUrl, JobSeeker jobSeeker, CurriculumVitae curriculumVitae) {
		super();
		this.photoName = photoName;
		this.photoUrl = photoUrl;
		this.jobSeeker = jobSeeker;
		this.curriculumVitae = curriculumVitae;
	}
}

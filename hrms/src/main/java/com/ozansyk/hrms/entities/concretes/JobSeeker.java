package com.ozansyk.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ozansyk.hrms.entities.abstracts.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","JobAdvertisement"})
@NoArgsConstructor
@Table(name="job_seekers")
public class JobSeeker extends User {
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="identity_number")
	private String identitynumber;
	
	@Column(name="birth_date")
	private LocalDate birthDate;

	public JobSeeker(String email, String password, String firstName, String lastName, String identitynumber,
			LocalDate birthDate) {
		super(email, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.identitynumber = identitynumber;
		this.birthDate = birthDate;
	}
}

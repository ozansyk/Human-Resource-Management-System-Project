package com.ozansyk.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ozansyk.hrms.entities.abstracts.User;

import lombok.Data;

@Data
@Entity
@Table(name="employers")
public class Employer extends User {
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="web_adress")
	private String webAdress;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="is_activated")
	private boolean isActivated;

}

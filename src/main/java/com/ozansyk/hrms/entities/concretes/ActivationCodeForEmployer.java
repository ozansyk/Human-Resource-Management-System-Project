package com.ozansyk.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ozansyk.hrms.entities.abstracts.ActivationCode;

import lombok.Data;

@Data
@Entity
@Table(name="")
public class ActivationCodeForEmployer extends ActivationCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="employer_id")
	private int employerId;
}

package com.ozansyk.hrms.entities.abstracts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="activation_codes")
public abstract class ActivationCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="activation_code")
	private String activationCode;
	
	@Column(name="is_confirmed")
	private boolean isConfimed;
	
	@Column(name="confirmed_time")
	private Date confirmedDate;
}

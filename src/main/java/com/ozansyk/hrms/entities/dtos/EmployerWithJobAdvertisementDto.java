package com.ozansyk.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerWithJobAdvertisementDto {
	private int id;
	private String employerName;
	private String jobName;
	private int numberOfPositions;
	private LocalDate publishDate;
	private LocalDate endDate;
	private boolean isActive;
}

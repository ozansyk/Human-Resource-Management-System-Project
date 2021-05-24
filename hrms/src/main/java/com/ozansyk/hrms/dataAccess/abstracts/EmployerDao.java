package com.ozansyk.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozansyk.hrms.entities.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer> {

}

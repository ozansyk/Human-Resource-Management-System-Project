package com.ozansyk.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozansyk.hrms.entities.concretes.Job;

public interface JobDao extends JpaRepository<Job, Integer> {
	Job getByJobName(String jobName);
}

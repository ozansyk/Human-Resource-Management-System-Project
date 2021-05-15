package com.ozansyk.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozansyk.hmrs.entities.concretes.Job;

public interface JobDao extends JpaRepository<Job, Integer> {
	
}

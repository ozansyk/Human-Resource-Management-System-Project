package com.ozansyk.hmrs.business.abstracts;

import java.util.List;

import com.ozansyk.hmrs.entities.concretes.Job;

public interface JobService {
	List<Job> getAll();
}

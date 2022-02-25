package com.ozansyk.hrms.business.abstracts;

import java.util.List;

import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.Job;

public interface JobService {
	DataResult<List<Job>> getAll();
	Result add(String jobName);
	boolean checkJobExist(Job job);
}

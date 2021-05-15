package com.ozansyk.hmrs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hmrs.business.abstracts.JobService;
import com.ozansyk.hmrs.dataAccess.abstracts.JobDao;
import com.ozansyk.hmrs.entities.concretes.Job;

@Service
public class JobManager implements JobService {
	
	private JobDao jobDao;

	@Autowired
	public JobManager(JobDao jobDao) {
		super();
		this.jobDao = jobDao;
	}

	@Override
	public List<Job> getAll() {
		return this.jobDao.findAll();
	}

}

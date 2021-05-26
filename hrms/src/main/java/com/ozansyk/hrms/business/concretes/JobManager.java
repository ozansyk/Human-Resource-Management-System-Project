package com.ozansyk.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.JobService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessDataResult;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.dataAccess.abstracts.JobDao;
import com.ozansyk.hrms.entities.concretes.Job;

@Service
public class JobManager implements JobService {
	
	private String jobNameCheckMessage = "";
	private JobDao jobDao;

	@Autowired
	public JobManager(JobDao jobDao) {
		super();
		this.jobDao = jobDao;
	}

	@Override
	public DataResult<List<Job>> getAll() {
		return new SuccessDataResult<List<Job>>(this.jobDao.findAll(), "Jobs listed.");
	}

	@Override
	public Result add(Job job) {
		if(checkJobExist(job)) {
			this.jobDao.save(job);
		return new SuccessResult("Job added.");
		} else {
			return new ErrorResult(jobNameCheckMessage);
		}
		
	}

	@Override
	public boolean checkJobExist(Job job) {
		for(Job j : this.jobDao.findAll()) {
			if(job.getJobName().equals(j.getJobName())) {
				jobNameCheckMessage = "Bu iş pozisyonu zaten mecvut!";
				return false;
			}
		}
		return true;
	}

}

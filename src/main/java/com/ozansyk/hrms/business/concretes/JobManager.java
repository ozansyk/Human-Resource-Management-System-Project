package com.ozansyk.hrms.business.concretes;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.JobService;
import com.ozansyk.hrms.business.constants.Messages;
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
		this.jobDao = jobDao;
	}

	@Override
	public DataResult<List<Job>> getAll() {
		return new SuccessDataResult<List<Job>>(this.jobDao.findAll(), Messages.jobListed);
	}

	@Override
	public Result add(Job job) {
		if(checkJobExist(job)) {
			this.jobDao.save(job);
			return new SuccessResult(Messages.jobSuccessAdd);
		} else {
			return new ErrorResult(jobNameCheckMessage);
		}
	}

	@Override
	public boolean checkJobExist(Job job) {
		Job existJob = this.jobDao.getByJobName(job.getJobName());
		if(!Objects.isNull(existJob)) {
			jobNameCheckMessage = Messages.jobChecktoAdd;
			return false;
		}
		return true;
	}

}

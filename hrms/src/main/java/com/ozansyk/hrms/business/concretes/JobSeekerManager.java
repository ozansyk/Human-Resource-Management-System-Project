package com.ozansyk.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.JobSeekerService;
import com.ozansyk.hrms.core.adapters.abstracts.MailCheckService;
import com.ozansyk.hrms.core.adapters.abstracts.MernisCheckService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessDataResult;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.dataAccess.abstracts.JobSeekerDao;
import com.ozansyk.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {
	
	private String registerFailedMessage = "";
	private JobSeekerDao jobSeekerDao;
	private MernisCheckService mernisCheckService;
	private MailCheckService mailCheckService;

	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao, MernisCheckService mernisCheckService, MailCheckService mailCheckService) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		this.mernisCheckService = mernisCheckService;
		this.mailCheckService = mailCheckService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "Job seekers listed.");
	}

	@Override
	public Result add(JobSeeker jobSeeker) {
		
		if(this.mailCheckService.sendCheckMail(jobSeeker) && this.mernisCheckService.checkIfRealPerson(jobSeeker)
				&& checkFieldsforRegister(jobSeeker)) {
			this.jobSeekerDao.save(jobSeeker);
			return new SuccessResult("Job seeker successfully signed up.");
		} else {
			return new ErrorResult(registerFailedMessage);
		}
	}

	@Override
	public boolean checkFieldsforRegister(JobSeeker jobSeeker) {
		for(JobSeeker seeker : jobSeekerDao.findAll()) {
			if(jobSeeker.getEmail().equals(seeker.getEmail()) ) {
				registerFailedMessage = "Bu mail ile daha önce kayıt olunmuş!";
				return false;
			}
			if(jobSeeker.getIdentitynumber().equals(seeker.getIdentitynumber())) {
				registerFailedMessage = "Bu TcNo ile daha önce kayıt olunmuş!";
				return false;
			}
			
			String[] emailsplits = jobSeeker.getEmail().split("@");
			if(emailsplits.length == 0) {
				registerFailedMessage = "Yazdığınız email, email formatında değil!";
				return false;
			}
		}
		return true;
	}

}

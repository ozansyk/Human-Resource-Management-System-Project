package com.ozansyk.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.JobSeekerService;
import com.ozansyk.hrms.business.constants.Messages;
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
		this.jobSeekerDao = jobSeekerDao;
		this.mernisCheckService = mernisCheckService;
		this.mailCheckService = mailCheckService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), Messages.jobSeekerListed);
	}

	@Override
	public Result add(String firstName, String lastName, String email, String password, String passwordConfirm, String identitynumber, 
			 int birthYear, int birthMonth, int birthDay) {
		
		if((password.equals(passwordConfirm)) == false) {
			return new ErrorResult("Şifreler uyuşmuyor!");
		}
		
		LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
		
		JobSeeker jobSeeker = new JobSeeker(email, password, firstName, lastName, identitynumber, birthDate);
		
		if(this.mailCheckService.sendCheckMail(jobSeeker) && this.mernisCheckService.checkIfRealPerson(jobSeeker)
				&& checkFieldsforRegister(jobSeeker)) {
			this.jobSeekerDao.save(jobSeeker);
			return new SuccessResult(Messages.jobSeekerSuccessRegistered);
		} else {
			return new ErrorResult(registerFailedMessage);
		}
	}

	@Override
	public boolean checkFieldsforRegister(JobSeeker jobSeeker) {
		if(this.jobSeekerDao.getByEmail(jobSeeker.getEmail()) != null) {
			registerFailedMessage = Messages.jobSeekerCheckFailedEmail;
			return false;
		}
		for(JobSeeker js : this.jobSeekerDao.findAll()) {
			if(js.getIdentitynumber().equals(jobSeeker.getIdentitynumber())) {
			registerFailedMessage = Messages.jobSeekerCheckFailedTc;
			return false;
		}
		}
		
		
		String[] emailsplits = jobSeeker.getEmail().split("@");
		if(emailsplits.length == 0) {
			registerFailedMessage = Messages.jobSeekerCheckFailedEmailFormat;
			return false;
		}
		return true;
	}

}

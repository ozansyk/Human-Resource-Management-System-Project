package com.ozansyk.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.EmployerService;
import com.ozansyk.hrms.core.adapters.abstracts.MailCheckService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessDataResult;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.dataAccess.abstracts.EmployerDao;
import com.ozansyk.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {
	
	private EmployerDao employerDao;
	private MailCheckService mailCheckService;

	@Autowired
	public EmployerManager(EmployerDao employerDao, MailCheckService mailCheckService) {
		super();
		this.employerDao = employerDao;
		this.mailCheckService = mailCheckService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "Employers listed.");
	}

	@Override
	public Result add(Employer employer) {
		
		if(this.mailCheckService.sendCheckMail(employer)) {
			this.employerDao.save(employer);
			return new SuccessResult("Employer successfully singed up.");
		} else {
			return new ErrorResult("Verification or anything failed.");
		}
	}

}

package com.ozansyk.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.EmployerService;
import com.ozansyk.hrms.business.constants.Messages;
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
	
	private String registerFailedMessage = "";
	private EmployerDao employerDao;
	private MailCheckService mailCheckService;

	@Autowired
	public EmployerManager(EmployerDao employerDao, MailCheckService mailCheckService) {
		this.employerDao = employerDao;
		this.mailCheckService = mailCheckService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), Messages.employerListed);
	}

	@Override
	public Result add(String email, String password, String passwordConfirmed, String companyName, String webAdress, String phoneNumber) {
		
		if(password.equals(passwordConfirmed) == false) {
			return new ErrorResult("Şifreler uyuşmuyor!");
		}
		boolean isActivated = false;
		Employer employer = new Employer(email, password, companyName, webAdress, phoneNumber, isActivated);
		
		if(this.mailCheckService.sendCheckMail(employer) && this.checkFieldsforRegister(employer)) {
			employer.setActivated(true);
			this.employerDao.save(employer);
			return new SuccessResult(Messages.employerSuccessRegistered);
		} else {
			return new ErrorResult(registerFailedMessage);
		}
	}

	@Override
	public DataResult<List<Employer>> getAllActiveJobAdvertisements() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getAllActiveJobAdvertisements());
	}
	
	@Override
	public boolean checkFieldsforRegister(Employer employer) {
		String[] webSplits = employer.getWebAdress().split("\\.", 2);
		String[] emailSplits = employer.getEmail().split("@", 2);
		
		for(Employer emp : this.employerDao.findAll()) {
			if(employer.getEmail().equals(emp.getEmail())) {
				registerFailedMessage = Messages.employerRegisterFailedEmail;
				return false;
			}
		}
		
		if(webSplits[webSplits.length-1].equals(emailSplits[emailSplits.length-1])) {
			return true;
			}
		registerFailedMessage = Messages.employerRegisterFailedEmailWeb;
		return false;
	}

}

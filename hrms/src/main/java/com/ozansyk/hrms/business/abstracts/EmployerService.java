package com.ozansyk.hrms.business.abstracts;

import java.util.List;

import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.Employer;

public interface EmployerService {
	
	DataResult<List<Employer>> getAll();
	Result add(Employer employer);
	boolean checkFieldsforRegister(Employer employer);
}

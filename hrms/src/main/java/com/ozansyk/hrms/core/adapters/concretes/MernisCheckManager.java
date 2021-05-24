package com.ozansyk.hrms.core.adapters.concretes;

import org.springframework.stereotype.Service;

import com.ozansyk.hrms.core.adapters.abstracts.MernisCheckService;
import com.ozansyk.hrms.entities.abstracts.User;

@Service
public class MernisCheckManager implements MernisCheckService {

	@Override
	public boolean checkIfRealPerson(User user) {
		return true;
	}

}

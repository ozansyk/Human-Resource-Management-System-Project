package com.ozansyk.hrms.core.adapters.concretes;

import org.springframework.stereotype.Service;

import com.ozansyk.hrms.core.adapters.abstracts.MailCheckService;
import com.ozansyk.hrms.entities.abstracts.User;

@Service
public class MailCheckManager implements MailCheckService {

	@Override
	public boolean sendCheckMail(User user) {
		return true;
	}

}

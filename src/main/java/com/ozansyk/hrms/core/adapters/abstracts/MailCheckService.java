package com.ozansyk.hrms.core.adapters.abstracts;

import com.ozansyk.hrms.entities.abstracts.User;

public interface MailCheckService {
	boolean sendCheckMail(User user);
}

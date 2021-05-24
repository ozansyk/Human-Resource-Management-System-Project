package com.ozansyk.hrms.core.adapters.abstracts;

import com.ozansyk.hrms.entities.abstracts.User;

public interface MernisCheckService {
	boolean checkIfRealPerson(User user);
}

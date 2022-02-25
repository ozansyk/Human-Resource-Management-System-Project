package com.ozansyk.hrms.business.abstracts;

import java.util.List;

import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.City;

public interface CityService {
	DataResult<List<City>> getAll();
	Result add(String cityName);
	Result delete(int cityId);
}

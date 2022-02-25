package com.ozansyk.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozansyk.hrms.business.abstracts.CityService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessDataResult;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.dataAccess.abstracts.CityDao;
import com.ozansyk.hrms.entities.concretes.City;

@Service
public class CityManager implements CityService {

	private CityDao cityDao;
	
	@Autowired
	public CityManager(CityDao cityDao) {
		this.cityDao = cityDao;
	}
	
	@Override
	public DataResult<List<City>> getAll() {
		return new SuccessDataResult<List<City>>(this.cityDao.findAll(), "Şehirler listelendi.");
	}
	
	@Override
	public Result add(String cityName) {
		
		if(this.cityDao.getByCityName(cityName) != null) {
			return new ErrorResult("Bu şehir zaten mevcut!");
		}
		
		City city = new City(cityName);
		this.cityDao.save(city);
		return new SuccessResult("Şehir eklendi.");
	}

	@Override
	public Result delete(int cityId) {
		this.cityDao.deleteById(cityId);
		return new SuccessResult("Şehir başarıyla silindi.");
	}
	
}

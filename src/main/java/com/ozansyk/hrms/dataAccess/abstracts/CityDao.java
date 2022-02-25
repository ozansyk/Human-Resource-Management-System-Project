package com.ozansyk.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozansyk.hrms.entities.concretes.City;

public interface CityDao extends JpaRepository<City, Integer> {
	City getByCityName(String cityName);
}

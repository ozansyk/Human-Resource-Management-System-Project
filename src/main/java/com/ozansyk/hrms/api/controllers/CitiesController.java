package com.ozansyk.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ozansyk.hrms.business.abstracts.CityService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.City;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
	
	private CityService cityService;

	@Autowired
	public CitiesController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<City>> getAll(){
		return this.cityService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody City city) {
		return this.cityService.add(city);
	}

	@GetMapping(value = "/getbyid/{id}")
	public DataResult<City> getById(@PathVariable int id) {
		return this.cityService.getById(id);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestParam int cityId) {
		return this.cityService.delete(cityId);
	}
	
}

package com.ozansyk.hrms.api.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ozansyk.hrms.business.abstracts.CurriculumVitaeService;
import com.ozansyk.hrms.business.abstracts.PhotoService;
import com.ozansyk.hrms.core.services.CloudinaryService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.concretes.Photo;

@RestController
@RequestMapping("/api/curriculumVitae")
public class CurriculumVitaesController {
	
	private CurriculumVitaeService curriculumVitaeService;
	private CloudinaryService cloudinaryService;
	private PhotoService photoService;

	@Autowired
	public CurriculumVitaesController(CurriculumVitaeService curriculumVitaeService, CloudinaryService cloudinaryService, PhotoService photoService) {
		this.curriculumVitaeService = curriculumVitaeService;
		this.cloudinaryService = cloudinaryService;
		this.photoService = photoService;
	}
	
	@GetMapping("/getCvById")
	public DataResult<CurriculumVitae> getCvById(int jobSeekerId) {
		return this.curriculumVitaeService.getCv(jobSeekerId);
	}
	
	@PostMapping("/createCv")
	public Result createCv(@RequestParam int jobSeekerId, @RequestParam String githubAddress, @RequestParam String linkedinAddress) {
		return this.curriculumVitaeService.addCv(jobSeekerId, githubAddress, linkedinAddress);
	}
	
	@PostMapping("/addEducationToCv")
	public Result addEducationToCv(@RequestParam String schoolName,@RequestParam  String department, @RequestParam int startTimeYear, 
			@RequestParam int startTimeMonth, @RequestParam int startTimeDay, @RequestParam Integer graduateTimeYear, 
			@RequestParam Integer graduateTimeMonth, @RequestParam Integer graduateTimeDay, @RequestParam boolean isGraduated, @RequestParam int jobSeekerId) {
		if(graduateTimeDay == null || graduateTimeMonth == null || graduateTimeYear == null) {
			graduateTimeDay = 0;
			graduateTimeMonth = 0;
			graduateTimeYear = 0;
		}
		return this.curriculumVitaeService.addEducationToCv(schoolName, department, startTimeYear, 
				startTimeMonth, startTimeDay, graduateTimeYear, graduateTimeMonth, graduateTimeDay, isGraduated, jobSeekerId);
		
	}
	
	@PostMapping("/addJobExperienceToCv")
	public Result addJobExperienceToCv(String workplaceName, String positionName, int startTimeYear, int startTimeMonth, int startTimeDay, 
			Integer endTimeYear, Integer endTimeMonth, Integer endTimeDay, boolean isWorkingNow, int jobSeekerId) {
		if(endTimeDay == null || endTimeMonth == null || endTimeYear == null) {
			endTimeDay = 0;
			endTimeMonth = 0;
			endTimeYear = 0;
		}
		return this.curriculumVitaeService.addJobExperienceToCv(workplaceName, positionName, startTimeYear, startTimeMonth, startTimeDay, endTimeYear, endTimeMonth, endTimeDay, isWorkingNow, jobSeekerId);
	}
	
	@PostMapping("/addLanguageToCv")
	public Result addLanguageToCv(String languageName, int languageLevel, int jobSeekerId) {
		return this.curriculumVitaeService.addLanguageToCv(languageName, languageLevel, jobSeekerId);
	}
	
	@PostMapping("/addPhotoToCv")
	public Result addPhotoToCv(@RequestParam MultipartFile multipartFile, @RequestParam int jobSeekerId) throws IOException {
		
		BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
		if(bufferedImage == null) {
			
			return new ErrorResult("Resim yüklenemedi.");
		}
		
		Map result = cloudinaryService.upload(multipartFile);
		Photo photo = new Photo((String)result.get("original_filename"),(String)result.get("url"),
				(String)result.get("public_id"));
		
		return this.photoService.add(photo, jobSeekerId);
	}
	
	@PostMapping("/addProgramingLanguageToCv")
	public Result addProgramingLanguageToCv(String programingLanguageName, int jobSeekerId) {
		return this.curriculumVitaeService.addProgramingLanguageToCv(programingLanguageName, jobSeekerId);
	}
	
	@PostMapping("/addCoverLetterToCv")
	public Result addCoverLetterToCv(String letter, int jobSeekerId) {
		return this.curriculumVitaeService.addCoverLetterToCv(letter, jobSeekerId);
	}
	
}

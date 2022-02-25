package com.ozansyk.hrms.api.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.ozansyk.hrms.entities.concretes.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ozansyk.hrms.business.abstracts.CoverLetterService;
import com.ozansyk.hrms.business.abstracts.CurriculumVitaeService;
import com.ozansyk.hrms.business.abstracts.EducationService;
import com.ozansyk.hrms.business.abstracts.JobExperienceService;
import com.ozansyk.hrms.business.abstracts.LanguageService;
import com.ozansyk.hrms.business.abstracts.PhotoService;
import com.ozansyk.hrms.business.abstracts.ProgramingLanguageService;
import com.ozansyk.hrms.core.services.CloudinaryService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.CurriculumVitae;
import com.ozansyk.hrms.entities.concretes.Photo;

@CrossOrigin
@RestController
@RequestMapping("/api/curriculumVitae")
public class CurriculumVitaesController {
	
	private CurriculumVitaeService curriculumVitaeService;
	private CloudinaryService cloudinaryService;
	private PhotoService photoService;
	private EducationService educationService;
	private JobExperienceService jobExperienceService;
	private LanguageService languageService;
	private ProgramingLanguageService programingLanguageService;
	private CoverLetterService coverLetterService;

	@Autowired
	public CurriculumVitaesController(CurriculumVitaeService curriculumVitaeService,
			CloudinaryService cloudinaryService, PhotoService photoService, EducationService educationService,
			JobExperienceService jobExperienceService, LanguageService languageService,
			ProgramingLanguageService programingLanguageService, CoverLetterService coverLetterService) {
		this.curriculumVitaeService = curriculumVitaeService;
		this.cloudinaryService = cloudinaryService;
		this.photoService = photoService;
		this.educationService = educationService;
		this.jobExperienceService = jobExperienceService;
		this.languageService = languageService;
		this.programingLanguageService = programingLanguageService;
		this.coverLetterService = coverLetterService;
	}
	
	@GetMapping("/getAllCv")
	public DataResult<List<CurriculumVitae>> getAllCv() {
		return this.curriculumVitaeService.getAllCv();
	}
	

	@GetMapping("/getCvById")
	public DataResult<CurriculumVitae> getCvById(int jobSeekerId) {
		return this.curriculumVitaeService.getCv(jobSeekerId);
	}
	
	
	//Create and add Cv details.
	@PostMapping("/createCv")
	public Result createCv(@RequestParam int jobSeekerId, @RequestParam String githubAddress, @RequestParam String linkedinAddress) {
		return this.curriculumVitaeService.addCv(jobSeekerId, githubAddress, linkedinAddress);
	}
	
	@PostMapping("/addEducationToCv")
	public Result addEducationToCv(@RequestBody Education education) {
		return this.educationService.addEducationToCv(education);
		
	}
	
	@PostMapping("/addJobExperienceToCv")
	public Result addJobExperienceToCv(String workplaceName, String positionName, int startTimeYear, int startTimeMonth, int startTimeDay, 
			Integer endTimeYear, Integer endTimeMonth, Integer endTimeDay, boolean isWorkingNow, int jobSeekerId) {
		if(endTimeDay == null || endTimeMonth == null || endTimeYear == null) {
			endTimeDay = 0;
			endTimeMonth = 0;
			endTimeYear = 0;
		}
		return this.jobExperienceService.addJobExperienceToCv(workplaceName, positionName, startTimeYear, startTimeMonth, startTimeDay, endTimeYear, endTimeMonth, endTimeDay, isWorkingNow, jobSeekerId);
	}
	
	@PostMapping("/addLanguageToCv")
	public Result addLanguageToCv(String languageName, int languageLevel, int jobSeekerId) {
		return this.languageService.addLanguageToCv(languageName, languageLevel, jobSeekerId);
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
		return this.programingLanguageService.addProgramingLanguageToCv(programingLanguageName, jobSeekerId);
	}
	
	@PostMapping("/addCoverLetterToCv")
	public Result addCoverLetterToCv(String letter, int jobSeekerId) {
		return this.coverLetterService.addCoverLetterToCv(letter, jobSeekerId);
	}
	
	
	//Update Cv details.
	@PostMapping("/updateCv")
	public Result updateCv(int jobSeekerId, String githubAddress, String linkedinAddress) {
		return this.curriculumVitaeService.updateCv(jobSeekerId, githubAddress, linkedinAddress);
	}
	
	
	//Delete from Cv
	@PostMapping("/deleteEducationFromCv")
	public Result deleteEducationFromCv(int jobSeekerId, int educationId) {
		return this.educationService.deleteEducationFromCv(jobSeekerId, educationId);
	}
	
	@PostMapping("/deleteJobExperienceFromCv")
	public Result deleteJobExperienceFromCv(int jobSeekerId, int jobExperienceId) {
		return this.jobExperienceService.deleteJobExperienceFromCv(jobSeekerId, jobExperienceId);
	}
	
	@PostMapping("/deleteLanguageFromCv")
	public Result deleteLanguageFromCv(int jobSeekerId, int languageId) {
		return this.languageService.deleteLanguageFromCv(jobSeekerId, languageId);
	}
	
	@PostMapping("/deletePhotoFormCv")
	public Result deletePhotoFromCv(int jobSeekerId, int photoId) {
		return this.photoService.deletePhotoFromCv(jobSeekerId, photoId);
	}
	
	@PostMapping("/deleteProgramingLanguageFromCv")
	public Result deleteProgramingLanguageFromCv(int jobSeekerId, int programingLanguageId) {
		return this.programingLanguageService.deleteProgramingLanguageFromCv(jobSeekerId, programingLanguageId);
	}
	
	@PostMapping("/deleteCoverLetterFromCv")
	public Result deleteCoverLetterFromCv(int jobSeekerId, int coverLetterId) {
		return this.coverLetterService.deleteCoverLetterFromCv(jobSeekerId, coverLetterId);
	}
	
}

package com.ozansyk.hrms.api.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.ozansyk.hrms.business.abstracts.PhotoService;
import com.ozansyk.hrms.core.services.CloudinaryService;
import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.ErrorResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.core.utilities.results.SuccessDataResult;
import com.ozansyk.hrms.core.utilities.results.SuccessResult;
import com.ozansyk.hrms.entities.concretes.Photo;

@RestController
@RequestMapping("/api/photos")
public class PhotosController {
	
	private CloudinaryService cloudinaryService;
	private PhotoService photoService;
	
	@Autowired
	public PhotosController(CloudinaryService cloudinaryService, PhotoService photoService) {
		super();
		this.cloudinaryService = cloudinaryService;
		this.photoService = photoService;
	}
	
	@GetMapping("/getAllPhotos")
	public DataResult<List<Photo>> getAllPhotos() {
		return new SuccessDataResult<List<Photo>>(this.photoService.getAllPhotos().getData());
	}
	
	@PostMapping("/uploadPhoto")
	public Result add(@RequestParam MultipartFile multipartFile, @RequestParam int jobSeekerId) throws IOException {
		
		BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
		if(bufferedImage == null) {
			
			return new ErrorResult("Resim yüklenemedi.");
		}
		
		Map result = cloudinaryService.upload(multipartFile);
		Photo photo = new Photo((String)result.get("original_filename"),(String)result.get("url"),
				(String)result.get("public_id"));
		
		return this.photoService.add(photo, jobSeekerId);
	}
	
	@PostMapping("/deletePhoto")
	public Result delete(@RequestParam int id) {
		return this.photoService.delete(id);
	}
	
	@GetMapping("/getPhotoById")
	public DataResult<Photo> getById(@RequestParam int id) {
		return this.photoService.getById(id);
	}
	
}

package com.ozansyk.hrms.business.abstracts;

import java.util.List;

import com.ozansyk.hrms.core.utilities.results.DataResult;
import com.ozansyk.hrms.core.utilities.results.Result;
import com.ozansyk.hrms.entities.concretes.Photo;

public interface PhotoService {
	DataResult<List<Photo>> getAllPhotos();
	Result add(Photo photo, int jobSeekerId);
	Result delete(int id);
	DataResult<Photo> getById(int id);
	Result deletePhotoFromCv(int jobSeekerId, int photoId);
}

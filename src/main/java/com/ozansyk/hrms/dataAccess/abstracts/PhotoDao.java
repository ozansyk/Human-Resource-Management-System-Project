package com.ozansyk.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozansyk.hrms.entities.concretes.Photo;

public interface PhotoDao extends JpaRepository<Photo, Integer> {
	Photo getById(int id);
}

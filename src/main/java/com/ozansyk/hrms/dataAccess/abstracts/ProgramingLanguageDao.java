package com.ozansyk.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozansyk.hrms.entities.concretes.ProgramingLanguage;

public interface ProgramingLanguageDao extends JpaRepository<ProgramingLanguage, Integer> {

}

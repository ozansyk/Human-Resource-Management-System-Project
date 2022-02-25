package com.ozansyk.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozansyk.hrms.entities.concretes.Language;

public interface LanguageDao extends JpaRepository<Language, Integer> {

}

package com.ozansyk.hrms.entities.dtos.responseDtos;

import com.ozansyk.hrms.entities.concretes.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
public class CurriculumVitaeDtoResponse {
    private int id;
    private String githubAddress;
    private String linkedinAddress;
    private List<Education> educations;
    private List<JobExperience> jobExperience;
    private List<Language> languages;
    private List<Photo> photos;
    private List<ProgramingLanguage> programingLanguages;
    private List<CoverLetter> coverLetters;

    public CurriculumVitaeDtoResponse(int id, String githubAddress, String linkedinAddress, List<Education> educations, List<JobExperience> jobExperience,
                                      List<Language> languages, List<Photo> photos, List<ProgramingLanguage> programingLanguages, List<CoverLetter> coverLetters) {
        this.id = id;
        this.githubAddress = githubAddress;
        this.linkedinAddress = linkedinAddress;
        this.educations = educations;
        this.jobExperience = jobExperience;
        this.languages = languages;
        this.photos = photos;
        this.programingLanguages = programingLanguages;
        this.coverLetters = coverLetters;
    }
}

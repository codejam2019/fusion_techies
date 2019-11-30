package com.xyz.query.service;

import com.google.common.base.Strings;
import com.xyz.query.dto.IdNameDto;
import com.xyz.query.dto.NameDto;
import com.xyz.query.dto.response.GenericResponse;
import com.xyz.query.entity.Language;
import com.xyz.query.exception.GrabbyException;
import com.xyz.query.repository.LanguageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LanguageService {

  private final LanguageRepository languageRepository;

  public LanguageService(LanguageRepository languageRepository){
    this.languageRepository = languageRepository;
  }

  @Transactional
  @CacheEvict({"allLanguages"})
  public IdNameDto createLanguage(NameDto languageDto){

    if(Strings.isNullOrEmpty(languageDto.getName())){
      throw new GrabbyException("Language name not provided");
    }

    if(languageDto.getName().length() > 50){
      throw new GrabbyException("Language name contains more than 50 character");
    }

    Optional<IdNameDto> existing = languageRepository.findLanguageByName(languageDto.getName());
    if(existing.isPresent()){
      throw new GrabbyException("Language with name "+ languageDto.getName() +" already exists");
    }

    Language application = Language.builder().name(languageDto.getName().trim()).build();

    log.info("Language Created");
    languageRepository.saveAndFlush(application);

    return IdNameDto.builder()
               .id(application.getId())
               .name(application.getName())
               .build();
  }

  @Transactional(readOnly = true)
  @Cacheable("allLanguages")
  public List<IdNameDto> getAllLanguages(){
    return languageRepository.getAllLanguages();
  }

  @Transactional
  @CacheEvict({"allLanguages"})
  public GenericResponse deleteLanguageById(Long id){
    if(id == null){
      throw new GrabbyException("Language id not provided");
    }
    Optional<Language> language = languageRepository.findById(id);
    if(!language.isPresent()){
      throw new GrabbyException("Language not exist with id: " + id);
    }
    languageRepository.deleteById(id);
    log.info("Language Deleted");
    return GenericResponse.builder().message("Language Deleted").build();
  }
}

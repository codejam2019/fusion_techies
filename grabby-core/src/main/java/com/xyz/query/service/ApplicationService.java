package com.xyz.query.service;

import com.google.common.base.Strings;
import com.xyz.query.dto.IdNameDto;
import com.xyz.query.dto.NameDto;
import com.xyz.query.dto.response.GenericResponse;
import com.xyz.query.entity.Application;
import com.xyz.query.exception.GrabbyException;
import com.xyz.query.repository.ApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ApplicationService {

  private final ApplicationRepository applicationRepository;

  public ApplicationService(ApplicationRepository applicationRepository){
    this.applicationRepository = applicationRepository;
  }

  @Transactional
  @CacheEvict(value = {"allApps"}, allEntries = true)
  public IdNameDto createApplication(NameDto applicationDto){

      if(Strings.isNullOrEmpty(applicationDto.getName())){
        throw new GrabbyException("Application name not provided");
      }

      if(applicationDto.getName().length() > 50){
        throw new GrabbyException("Application name contains more than 50 character", HttpStatus.BAD_REQUEST);
      }

      Optional<IdNameDto> existing = applicationRepository.findApplicationByName(applicationDto.getName());
      if(existing.isPresent()){
        throw new GrabbyException("Application with name "+ applicationDto.getName() +" already exists",HttpStatus.CONFLICT);
      }

      Application application = Application.builder().name(applicationDto.getName().trim()).build();

      log.info("Application Created");
      applicationRepository.saveAndFlush(application);

      return IdNameDto.builder()
                 .id(application.getId())
                 .name(application.getName())
                 .build();
  }

  @Transactional(readOnly = true)
  @Cacheable("allApps")
  public List<IdNameDto> getAllApplication(){
    return applicationRepository.getAllApplication();
  }

  @Transactional
  @CacheEvict(value = {"allApps"}, allEntries = true)
  public GenericResponse deleteApplicationById(Long id){
    if(id == null){
      throw new GrabbyException("Application id not provided",HttpStatus.BAD_REQUEST);
    }
    Optional<Application> application = applicationRepository.findById(id);
    if(!application.isPresent()){
      throw new GrabbyException("Application not exist with id: " + id,HttpStatus.NOT_FOUND);
    }
    applicationRepository.deleteById(id);
    log.info("Application Deleted");
    return GenericResponse.builder().message("Application Deleted").build();
  }

  public boolean isExists(Long id){
    return applicationRepository.existsById(id);
  }
}

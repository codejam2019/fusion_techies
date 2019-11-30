package com.xyz.query.Controller;

import com.xyz.query.dto.IdNameDto;
import com.xyz.query.dto.NameDto;
import com.xyz.query.dto.response.GenericResponse;
import com.xyz.query.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/application")
public class ApplicationController {

  private final ApplicationService applicationService;

  public ApplicationController(ApplicationService applicationService){
    this.applicationService = applicationService;
  }

  @PostMapping
  public ResponseEntity<IdNameDto> createApplication(@RequestBody NameDto applicationDto)
  {
    return new ResponseEntity(applicationService.createApplication(applicationDto),HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<?> updateApplication() {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @GetMapping
  public List<IdNameDto> getApplication() {
    return applicationService.getAllApplication();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getApplicationById() {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @DeleteMapping("/{id}")
  public GenericResponse deleteApplication(@PathVariable(name = "id") Long id) {
    return applicationService.deleteApplicationById(id);
  }

  //TODO App Login Management APIs

  //TODO APP Authentication APIs
}

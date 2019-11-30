package com.xyz.query.poc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/poc")
public class PocController {

  @Autowired
  private CustomerService customerService;

  @PostMapping("/download")
  ResponseEntity<?> uploadFileWithData(@ModelAttribute TestModel model){
    log.info("File Exist: {}",model.getImage().getOriginalFilename());
    log.info("Data: {}", model);
    log.info("Dto: {}",model.getDto());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/customer")
  public ResponseEntity<?> createCustomer(){
    customerService.createNew();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/customer")
  public ResponseEntity<?> getAllCustomer(){
    return new ResponseEntity<>(customerService.findAll(),HttpStatus.OK);
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<?> getAllCustomer(@PathVariable("id") Long id){
    return new ResponseEntity<>(customerService.getById(id),HttpStatus.OK);
  }


}

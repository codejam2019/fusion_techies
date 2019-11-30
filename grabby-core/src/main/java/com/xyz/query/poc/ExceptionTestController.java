package com.xyz.query.poc;

import com.xyz.query.exception.GrabbyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api/v1/exception")
public class ExceptionTestController {

  @Autowired
  private CustomerRepo customerRepo;

  @PersistenceContext
  private EntityManager entityManager;

  @GetMapping("/unchecked/1")
  public ResponseEntity<?> uncheckedException1(){

    int i = 0;
    int j = 6;
    int z = j/i;
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/unchecked/2")
  public ResponseEntity<?> uncheckedException2(){

    Integer i = null;
    i.equals("1");
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/grabby")
  public ResponseEntity<?> grabby(){
    throw new GrabbyException("Exception Message",HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/bad-request/1")
  public ResponseEntity<?> badRequest1(@RequestParam(required = true) Long id){
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/bad-request/2")
  public ResponseEntity<?> badRequest2(@Valid @RequestBody(required = true) TestModel model){
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/db-1")
  public ResponseEntity<?> db1(@RequestBody  Customer customer){
    log.debug("Customer: {}",customer);
    customerRepo.save(customer);
    log.debug("Cusomer Saved: {}",customer);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/db-2")
  public ResponseEntity<?> db2(){

    Query query = entityManager.createQuery("SELECT c FROM Customer c where  c.id = 1000000");
    Customer customer1 = (Customer) query.getSingleResult();
    log.debug("Cusomer Saved: {}",customer1);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

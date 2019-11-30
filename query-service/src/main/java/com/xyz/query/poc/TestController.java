package com.xyz.query.poc;

import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

  @GetMapping("/sentry")
  public ResponseEntity<?> sentryTest(){
     //Sentry.init("https://3f4e2fabef0e495cb14ed7e5bed9e24f@sentry.io/1498169");
    try{
      Long i  = null;
      i.shortValue();
    } catch (Exception e){
      Sentry.capture(e);
      log.error("Error: ",e);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

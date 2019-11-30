package com.xyz.query.sbm;

import com.xyz.query.dto.response.ResponseMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/sbm")
public class ScreenBrightnessManagerAppController {

  private final SbmDeviceService sbmDeviceService;

  public ScreenBrightnessManagerAppController(SbmDeviceService sbmDeviceService){
    this.sbmDeviceService = sbmDeviceService;
  }

  @PostMapping("/register")
  public ResponseEntity<SbmDevice> registerDevice(@RequestBody SbmDevice sbmDevice){
    return new ResponseEntity<>(sbmDeviceService.registerDevice(sbmDevice), HttpStatus.OK);
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<?> getDeviceByUserId(@PathVariable("id") String userId){
    return null;
  }

  @PostMapping("/image")
  public ResponseEntity<ResponseMessageDto> uploadImage(MultipartFile image) throws IOException {

    image.transferTo(new File("/home/akshay/bms/"));

    return new ResponseEntity<>(ResponseMessageDto.builder().message("File Uploaded Successfully").build(),HttpStatus.OK);
  }
}

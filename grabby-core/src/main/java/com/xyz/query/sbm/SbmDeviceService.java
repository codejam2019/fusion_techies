package com.xyz.query.sbm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Slf4j
public class SbmDeviceService {

  private final SbmDeviceRepo sbmDeviceRepo;

  public SbmDeviceService(SbmDeviceRepo sbmDeviceRepo){
    this.sbmDeviceRepo = sbmDeviceRepo;
  }

  @Transactional
  public SbmDevice registerDevice(SbmDevice sbmDevice){
    sbmDevice.setLocalDateTime(LocalDateTime.now(ZoneId.of("UTC")));
    sbmDevice = sbmDeviceRepo.saveAndFlush(sbmDevice);
    return sbmDevice;
  }


}

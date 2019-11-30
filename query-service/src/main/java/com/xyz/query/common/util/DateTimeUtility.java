package com.xyz.query.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateTimeUtility {

  public static LocalDateTime getCurrentTimeInSpecificTimeZone(long currentLocalTimeInMilliseconds, ZoneOffset timeZone){
    return   Instant.ofEpochMilli(currentLocalTimeInMilliseconds)
        .atOffset(timeZone)
        .toLocalDateTime();
  }

  public static LocalDateTime getCurrentTimeInUTC(long currentLocalTimeInMilliseconds){
    return  getCurrentTimeInSpecificTimeZone(currentLocalTimeInMilliseconds,ZoneOffset.UTC);
  }

  public static LocalDateTime getCurrentTimeUTC(){
    return LocalDateTime.now(ZoneOffset.UTC);
  }

}

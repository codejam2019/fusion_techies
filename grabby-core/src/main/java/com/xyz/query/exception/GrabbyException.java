package com.xyz.query.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrabbyException extends RuntimeException{

  @Getter
  @Setter
  private int status;

  @Getter
  @Setter
  @Builder.Default
  private boolean triggerSentryEvent = false;

  public GrabbyException(String message) {
    super(message);
  }

  public GrabbyException(String message, Throwable cause){
    super(message,cause);
  }

  public GrabbyException(String message, HttpStatus status) {
    super(message);
    this.status = status.value();
  }

  public GrabbyException(String message, Throwable cause,HttpStatus status){
    super(message,cause);
    this.status = status.value();
  }




}

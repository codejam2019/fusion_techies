package com.xyz.query.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "isError", "status", "message"})
public class ResponseMessageDto {
  @JsonProperty("isError")
  @Builder.Default
  private boolean isError = false;

  private String message;

  private int status;

  private ResponseMessageDto(String message){
    this.message = message;
  }

  public ResponseMessageDto(boolean isError, String message) {
    super();
    this.isError = isError;
    this.message = message;
  }
}

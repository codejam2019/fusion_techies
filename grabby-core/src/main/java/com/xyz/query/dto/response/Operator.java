package com.xyz.query.dto.response;

import lombok.Getter;
import lombok.Setter;

public enum  Operator {

  EQUAL("="), NOT_EQUAL("!="), IS("IS"), IN("IN"), NOT_IN("NOT IN"), GREATER_THAN(">"), GREATER_EQUAL(">="), LESS_THAN("<"), LESS_EQUAL("<=");

  @Getter
  @Setter
  private String value;

  Operator(String value){
    this.value = value;
  }
}

package com.xyz.query.dto.response;

import lombok.Getter;
import lombok.Setter;

public enum BooleanOperator {

  OR("OR"), AND("AND"), NOT("NOT");

  @Getter
  @Setter
  private String value;

  BooleanOperator(String value){
    this.value = value;
  }
}

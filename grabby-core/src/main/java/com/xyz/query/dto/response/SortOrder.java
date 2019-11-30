package com.xyz.query.dto.response;

import lombok.Getter;
import lombok.Setter;

public enum  SortOrder {

  ASC("ASC"), DESC("DESC");

  @Getter
  @Setter
  private String value;

  SortOrder(String value){
    this.value = value;
  }
}

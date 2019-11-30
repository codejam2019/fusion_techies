package com.xyz.query.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Sorting {

  private int srNo;

  private String field;

  private SortOrder sortOrder;

  public String getSorting(){
    return  field  + " " + sortOrder.getValue();
  }
}

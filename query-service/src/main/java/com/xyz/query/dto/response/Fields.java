package com.xyz.query.dto.response;

import com.xyz.query.common.util.Utils;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Fields {
  private Set<String> selectedFields;

  public String prepareSelectedFields(){
    String queryString = "e";
    if(!Utils.isEmptyOrNull(selectedFields)) {
      selectedFields.remove("");
      selectedFields.remove(null);
      queryString = String.join(", ", selectedFields);
    }
    return queryString;
  }

}

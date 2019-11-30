package com.xyz.query.dto.request;

import com.xyz.query.dto.response.BooleanOperator;
import com.xyz.query.dto.response.Filter;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FilterGroup {

  private Integer srNo;

  private List<Filter> filters;

  @Builder.Default
  private BooleanOperator booleanOperator = BooleanOperator.AND;
}

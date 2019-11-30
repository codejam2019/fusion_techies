package com.xyz.query.dto.request;

import com.xyz.query.dto.response.Filter;
import com.xyz.query.dto.response.Sorting;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageSortFilters {

  @Builder.Default
  private int pageNumber = 1;

  @Builder.Default
  private int recordsPerPage = 100;

  private Set<String> fields;

  private List<Filter> filters;

  private List<FilterGroup> filterGroup;

  private Set<Sorting> sorting;

  public int getPageNumber() {
    return pageNumber - 1;
  }
}

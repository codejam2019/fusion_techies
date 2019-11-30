package com.xyz.query.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pager {

  private Long totalRecords;

  private Integer recordsPerPage;

  private Integer filteredRecords;

  private Integer pageNumber;
}

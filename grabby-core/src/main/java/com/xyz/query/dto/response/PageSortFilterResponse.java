package com.xyz.query.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageSortFilterResponse {

  List<?> data;

  private Pager pager;

}

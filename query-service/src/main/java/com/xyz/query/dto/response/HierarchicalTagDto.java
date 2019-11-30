package com.xyz.query.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HierarchicalTagDto {

  private Long id;

  private String name;

  private Boolean isVisible;

  private HierarchicalTagDto parentTag;
}

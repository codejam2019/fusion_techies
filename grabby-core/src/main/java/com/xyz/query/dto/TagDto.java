package com.xyz.query.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {

  private Long id;

  private String name;

  private String slug;

  @Builder.Default
  private Boolean isVisible = true;

  private Long parentTagId;

  @Builder.Default
  private List<TagDto> childTags = new ArrayList<>();

  @JsonIgnore
  @Builder.Default
  private boolean shouldIgnore = false;

  @JsonIgnore
  @Builder.Default
  private boolean visited = false;

  @JsonIgnore
  private Long postId;

  public TagDto(Long id, String name, Boolean isVisible, Long parentTagId) {
    this.id = id;
    this.name = name;
    this.isVisible = isVisible;
    this.parentTagId = parentTagId;
  }

  public TagDto(Long id, String name, String slug, Boolean isVisible, Long parentTagId) {
    this.id = id;
    this.name = name;
    this.isVisible = isVisible;
    this.parentTagId = parentTagId;
    this.slug = slug;
  }

  public TagDto(Long id, String name, Boolean isVisible, Long parentTagId, Long postId) {
    this.id = id;
    this.name = name;
    this.isVisible = isVisible;
    this.parentTagId = parentTagId;
    this.postId = postId;
  }
}

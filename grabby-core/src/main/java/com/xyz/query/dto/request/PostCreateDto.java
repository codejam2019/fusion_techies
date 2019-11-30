package com.xyz.query.dto.request;

import com.xyz.query.entity.ContentType;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDto {

  private String title;

  private String url;

  private String text;

  private String richText;

  private Set<Long> tags;

  @Builder.Default
  @Min(value = 0, message = "Age can't be less than 0")
  private Integer minAge = 0;

  @Builder.Default
  private Integer maxAge = 100;

  @Builder.Default
  private Boolean isVisible = true;

  @NotNull(message = "Content type should be provided")
  private ContentType contentType;

  @NotNull(message = "Language Id should be provided")
  private Long languageId;
}

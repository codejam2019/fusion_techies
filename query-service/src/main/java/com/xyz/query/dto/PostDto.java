package com.xyz.query.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xyz.query.entity.ContentType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

  private Long id;

  private String title;

  private ContentType contentType;

  private String text;

  private String url;

  private Long languageId;

  private Long likes;

  private Long disLikes;

  private Long downloads;

  private Long views;

  private Boolean isVisible;

  private Integer minAge;

  private Integer maxAge;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime creationDate;

  private Long ownerId;

  /*private Set<Long> appIds;*/

  private Long appId;

  private Set<Long> tagIds;

  private Set<Long> blockedBy;

  private Set<Long> dislikedBy;

  private Set<Long> likedBy;

  private Set<TagDto> tags;

  public PostDto(Long id, String title, ContentType contentType, String text, String url, Long languageId, Long likes, Long disLikes, Long downloads, Long views, Boolean isVisible) {
    this.id = id;
    this.title = title;
    this.contentType = contentType;
    this.text = text;
    this.url = url;
    this.languageId = languageId;
    this.likes = likes;
    this.disLikes = disLikes;
    this.downloads = downloads;
    this.views = views;
    this.isVisible = isVisible;
  }

  public PostDto(Long id, String title, ContentType contentType, String text, String url, Long languageId, Long likes, Long disLikes, Long downloads, Long views, Boolean isVisible, Integer minAge, Integer maxAge, LocalDateTime creationDate) {
    this.id = id;
    this.title = title;
    this.contentType = contentType;
    this.text = text;
    this.url = url;
    this.languageId = languageId;
    this.likes = likes;
    this.disLikes = disLikes;
    this.downloads = downloads;
    this.views = views;
    this.isVisible = isVisible;
    this.minAge = minAge;
    this.maxAge = maxAge;
    this.creationDate = creationDate;
  }

  public PostDto(Long id, String title, ContentType contentType, String text, String url, Long languageId, Long likes, Long disLikes, Long downloads, Long views, Boolean isVisible, Integer minAge, Integer maxAge, LocalDateTime creationDate, Long ownerId, Long appId, Set<Long> tagIds) {
    this.id = id;
    this.title = title;
    this.contentType = contentType;
    this.text = text;
    this.url = url;
    this.languageId = languageId;
    this.likes = likes;
    this.disLikes = disLikes;
    this.downloads = downloads;
    this.views = views;
    this.isVisible = isVisible;
    this.minAge = minAge;
    this.maxAge = maxAge;
    this.creationDate = creationDate;
    this.ownerId = ownerId;
    this.appId = appId;
    this.tagIds = tagIds;
  }
}

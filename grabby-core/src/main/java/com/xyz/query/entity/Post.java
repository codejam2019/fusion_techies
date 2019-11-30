package com.xyz.query.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "title")
  private String title;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "content_type")
  private ContentType contentType;

  @Column(name = "text")
  private String text;

  @Column(name = "rich_text")
  private String richText;

  @Column(name = "url")
  private String url;

  @ManyToOne
  @JoinColumn(name = "language_id", referencedColumnName = "id")
  private Language language;

  @Column(name = "likes")
  private Long likes;

  @Column(name = "dis_likes")
  private Long disLikes;

  @Column(name = "download")
  private Long downloads;

  @Column(name = "shares")
  private Long shares;

  @Column(name = "views")
  private Long views;

  @Column(name = "is_visible")
  private Boolean isVisible;

  @Column(name = "min_age")
  private Integer minAge;

  @Column(name = "max_age")
  private Integer maxAge;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id", referencedColumnName = "id")
  private User owner;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "app_id", referencedColumnName = "id")
  private Application application;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "post_tag",
      joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
  @Builder.Default
  private Set<Tag> tags = new HashSet<>();
}

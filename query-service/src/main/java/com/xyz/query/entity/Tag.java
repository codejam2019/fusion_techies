package com.xyz.query.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag")
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "slug")
  private String slug;

  @Column(name = "is_visible")
  private Boolean isVisible;

  @ManyToOne
  @JoinColumn(name = "parent_tag_id")
  private Tag parent;

  @Column(name = "app_id")
  private Long appId;

  @ManyToMany(mappedBy = "tags",fetch = FetchType.LAZY)
  @JsonIgnore
  @Builder.Default
  private Set<Post> posts = new HashSet<>();

  /*@ManyToMany(mappedBy = "blockedTags",fetch = FetchType.LAZY)
  @JsonIgnore
  @Builder.Default
  private Set<User> blockedBy = new HashSet<>();*/

  /*@ManyToMany(mappedBy = "preferredTags",fetch = FetchType.LAZY)
  @JsonIgnore
  @Builder.Default
  private Set<User> preferredBy = new HashSet<>();*/
}


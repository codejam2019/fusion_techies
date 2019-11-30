package com.xyz.query.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "application")
public class Application {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  /*@ManyToMany(mappedBy = "apps")
  @JsonIgnore
  @Builder.Default
  private Set<Post> posts = new HashSet<>();*/

  /*@ManyToMany(mappedBy = "apps")
  @JsonIgnore
  @Builder.Default
  private Set<User> users = new HashSet<>();*/
}

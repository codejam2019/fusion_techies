package com.xyz.query.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "email")
  private String email;

  @Column(name = "mobile")
  private String mobile;

  @Column(name = "password")
  private String password;

  @Column(name = "user_type")
  @Enumerated(EnumType.ORDINAL)
  private UserType userType;

  @Column(name = "age")
  private Integer age;

  @Column(name = "gender")
  @Enumerated(EnumType.ORDINAL)
  private Gender gender;

  @ManyToMany
  @JoinTable(name = "user_language",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"))
  private Set<Language> languages;

  @ManyToMany
  @JoinTable(name = "user_preferred_tag",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
  private Set<Post> preferredTags;
}

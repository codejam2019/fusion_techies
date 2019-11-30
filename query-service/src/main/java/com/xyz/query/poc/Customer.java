package com.xyz.query.poc;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

  @Column(name = "address")
  private String address;

  @Column(name = "email")
  private String email;
}

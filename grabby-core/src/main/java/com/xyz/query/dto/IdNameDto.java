package com.xyz.query.dto;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IdNameDto {
  private Long id;

  private String name;
}

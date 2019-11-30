package com.xyz.query.sbm;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SbmDeviceDto {

  private Long id;

  private String os;

  private String buildNumber;

  private String manufacturer;

  private String userId;

  private LocalDateTime localDateTime;
}

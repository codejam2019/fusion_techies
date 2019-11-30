package com.xyz.query.sbm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sbm_device")
public class SbmDevice {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String os;

  private String buildNumber;

  private String manufacturer;

  private String userId;

  @Column(name = "creation_date")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime localDateTime;
}

package com.xyz.query.poc;

import com.xyz.query.dto.IdNameDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
class TestModel{
  private MultipartFile image;

  //@NotNull(message = "Name must be provided")
  @Size(min = 2, max = 6)
  private String name;

  private Long id;

  private IdNameDto dto;
}
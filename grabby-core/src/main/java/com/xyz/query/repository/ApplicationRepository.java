package com.xyz.query.repository;

import com.xyz.query.dto.IdNameDto;
import com.xyz.query.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

  @Query("SELECT new com.orientech.grabby.core.dto.IdNameDto(a.id,a.name) FROM Application a WHERE a.name = :name")
  Optional<IdNameDto> findApplicationByName(@Param("name") String name);

  @Query("SELECT new com.orientech.grabby.core.dto.IdNameDto(a.id,a.name) FROM Application a")
  List<IdNameDto> getAllApplication();

  boolean existsById(Long id);

}

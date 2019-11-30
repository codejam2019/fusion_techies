package com.xyz.query.repository;

import com.xyz.query.dto.IdNameDto;
import com.xyz.query.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {

  @Query("SELECT new com.orientech.grabby.core.dto.IdNameDto(l.id,l.name) FROM Language l WHERE l.name = :name")
  public Optional<IdNameDto> findLanguageByName(@Param("name") String name);

  @Query("SELECT new com.orientech.grabby.core.dto.IdNameDto(l.id,l.name) FROM Language l")
  public List<IdNameDto> getAllLanguages();
}

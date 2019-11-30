package com.xyz.query.repository;

import com.xyz.query.dto.IdNameDto;
import com.xyz.query.entity.PostAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostActionRepository extends JpaRepository<PostAction, Long> {

  @Query("SELECT new com.orientech.grabby.core.dto.IdNameDto(pa.id ,pa.name) FROM PostAction pa")
  List<IdNameDto> getAllPostActions();
}

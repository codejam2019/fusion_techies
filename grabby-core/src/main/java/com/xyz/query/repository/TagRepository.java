package com.xyz.query.repository;

import com.xyz.query.dto.TagDto;
import com.xyz.query.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

  @Query("SELECT new com.orientech.grabby.core.dto.TagDto(t.id,t.name, t.slug, t.isVisible,t.parent.id) FROM Tag t WHERE t.id = :id")
  TagDto getTagById(@Param("id")Long id);

  @Query("SELECT new com.orientech.grabby.core.dto.TagDto(t.id,t.name, t.slug, t.isVisible,t.parent.id) FROM Tag t WHERE t.id = :id AND t.appId = :appId")
  TagDto getTagByIdAndAppId(@Param("id")Long id,@Param("appId") Long appId);

  @Query("SELECT new com.orientech.grabby.core.dto.TagDto(t.id,t.name, t.slug, t.isVisible,t.parent.id) FROM Tag t")

  List<TagDto> getAllTags();

  @Query("SELECT new com.orientech.grabby.core.dto.TagDto(t.id,t.name, t.slug, t.isVisible,t.parent.id) FROM Tag t WHERE t.appId = :appId")
  List<TagDto> getAllTagsByAppId(@Param("appId") Long appId);

  @Query("SELECT new com.orientech.grabby.core.dto.TagDto(t.id,t.name,t.isVisible,t.parent.id) FROM Tag t WHERE t.isVisible = :isVisible ")
  List<TagDto> getAllVisibleTagsByVisibility(@Param("isVisible") Boolean isVisible);

  @Query("SELECT new com.orientech.grabby.core.dto.TagDto(t.id,t.name,t.isVisible,t.parent.id) FROM Tag t WHERE t.isVisible = :isVisible AND t.appId = :appId")
  List<TagDto> getAllVisibleTagsByVisibilityAndAppId(@Param("isVisible") Boolean isVisible,@Param("appId") Long appId);

  @Query("SELECT new com.orientech.grabby.core.dto.TagDto(t.id,t.name,t.isVisible,t.parent.id,p.id) FROM Tag t INNER JOIN t.posts p WHERE p.id IN (:postIds)")
  List<TagDto> getTagsByPostIdsWithPostIds(@Param("postIds") List<Long> postIds);

  boolean existsById(Long id);

  Optional<Tag> findByIdAndAppId(Long id, Long appId);
}

package com.xyz.query.repository;

import com.xyz.query.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

  //List<Post> findByTags_IdInAndApps_Id(Set<Long> tagsId,List<Long> applicationIds,Pageable pageable);

  @Modifying
  @Query("UPDATE Post p SET p.likes = p.likes + 1 WHERE p.id IN (:postIds) AND p.application.id = :appId")
  int increaseLikeCountByOne(@Param("postIds") List<Long> postIds, @Param("appId") Long appId);

  @Modifying
  @Query("UPDATE Post p SET p.disLikes = p.disLikes + 1 WHERE p.id IN (:postIds) AND p.application.id = :appId")
  int increaseDisLikeCountByOne(@Param("postIds") List<Long> postIds, @Param("appId") Long appId);

  @Modifying
  @Query("UPDATE Post p SET p.downloads = p.downloads + 1 WHERE p.id IN (:postIds) AND p.application.id = :appId")
  int increaseDownloadCountByOne(@Param("postIds") List<Long> postIds, @Param("appId") Long appId);

  @Modifying
  @Query("UPDATE Post p SET p.shares = p.shares + 1 WHERE p.id IN (:postIds)")
  int increaseShareByOne(@Param("postIds") List<Long> postIds);

  @Modifying
  @Query("UPDATE Post p SET p.views = p.views + 1 WHERE p.id IN (:postIds)")
  int increaseViewByOne(@Param("postIds") List<Long> postIds);

  @Modifying
  @Query("DELETE FROM Post WHERE id IN (:postIds)")
  int deletePosts(@Param("postIds") List<Long> postIds);

}

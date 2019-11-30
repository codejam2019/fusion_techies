package com.xyz.query.service;

import com.google.common.base.Strings;
import com.xyz.query.dto.TagDto;
import com.xyz.query.dto.response.GenericResponse;
import com.xyz.query.entity.Tag;
import com.xyz.query.exception.GrabbyException;
import com.xyz.query.repository.TagRepository;
import com.xyz.query.common.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TagService {

  private final TagRepository tagRepository;

  private final ApplicationService appService;

  public TagService(TagRepository tagRepository, ApplicationService appService) {
    this.tagRepository = tagRepository;
    this.appService = appService;
  }

  @Transactional
  @CacheEvict(value = {"tagListById","tagById","allTags","hierarchicalTagList","tagById"},allEntries = true)
  public TagDto createTag(TagDto tagDto, Long appId) {

    //Can be remove this code block in future
    if(!appService.isExists(appId)){
      throw new GrabbyException("Application with Id: " + tagDto.getParentTagId() + " not exist", HttpStatus.CONFLICT);
    }

    Optional<Tag> parentTag = tagRepository.findByIdAndAppId(tagDto.getParentTagId(),appId);
    if (tagDto.getParentTagId() != null && !isExist(tagDto.getParentTagId())) {
        throw new GrabbyException("Parent Tag with Id: " + tagDto.getParentTagId() + " not exist", HttpStatus.CONFLICT);
    }

    Tag tag = Tag.builder()
                  .name(tagDto.getName())
                  .slug(Strings.isNullOrEmpty(tagDto.getSlug()) ? createSlug(tagDto.getName()) : tagDto.getSlug())
                  .isVisible(tagDto.getIsVisible() != null ? tagDto.getIsVisible() : true)
                  .parent(parentTag.orElse(null))
                  .appId(appId)
                  .build();
    tag = tagRepository.save(tag);
    tagDto.setId(tag.getId());
    tagDto.setName(tag.getName());
    tagDto.setIsVisible(tag.getIsVisible());
    tagDto.setSlug(tag.getSlug());
    tagDto.setParentTagId(tag.getParent() != null ? tag.getParent().getId() : null);
    log.info("New Tag is created");
    return tagDto;
  }

  private boolean isExist(Long id){
    return tagRepository.existsById(id);
  }

  private String createSlug(String name){
    if(name != null) {
      name = name.trim();
      name = name.replaceAll("\\s+", "-");
      name = name.toLowerCase();
    }
    return name;
  }

  @Transactional
  @CacheEvict( value = {"tagListById","tagById","allTags","hierarchicalTagList","tagById"},allEntries = true)
  public GenericResponse deleteTagById(Long id, Long appId) {
    if (id == null) {
      throw new GrabbyException("Tag id is not provided", HttpStatus.BAD_REQUEST);
    }

    //TODO perforamance imprrovement
    Optional<Tag> tag = tagRepository.findByIdAndAppId(id,appId);
    if (!tag.isPresent()) {
      throw new GrabbyException("Tag not exist with id: " + id, HttpStatus.NOT_FOUND);
    }

    if(!Utils.isEmptyOrNull(tag.get().getPosts())){
      throw new GrabbyException("There are " + tag.get().getPosts().size() +" posts associated with this tag. You can hide the tag or else update the tags for those posts.", HttpStatus.CONFLICT);
    }

    tagRepository.deleteById(id);
    log.info("Tag Deleted");
    return GenericResponse.builder().message("Tag Deleted").build();
  }

  @Transactional(readOnly = true)
  @Cacheable("tagById")
  public TagDto getTagById(Long id, Long appId) {
    TagDto tagDto = tagRepository.getTagByIdAndAppId(id,appId);
    if (tagDto == null) {
      throw new GrabbyException("Tag not exist with id: " + id, HttpStatus.NOT_FOUND);
    }
    return tagDto;
  }

  @Transactional(readOnly = true)
  @Cacheable("allTags")
  public List<TagDto> getAllTags(Boolean tagVisibility, Long appId) {
    List<TagDto> tagDtos;
    if(tagVisibility != null){
      tagDtos = tagRepository.getAllVisibleTagsByVisibilityAndAppId(tagVisibility,appId);
    } else{
      tagDtos = tagRepository.getAllTagsByAppId(appId);
    }
    return tagDtos;
  }

  @Transactional(readOnly = true)
  @Cacheable("hierarchicalTagList")
  public List<TagDto> getTagsInHierarchicalFashion(Long appId) {
    List<TagDto> allTags = tagRepository.getAllTagsByAppId(appId);
    return tagsToHierarchicalTagDtos(allTags);
  }

  @Transactional(readOnly = true)
  @Cacheable("tagListById")
  public List<TagDto> tagDtosByPostIdsWithPostId(List<Long> postIds){
    return tagRepository.getTagsByPostIdsWithPostIds(postIds);
  }

  private List<TagDto> tagsToHierarchicalTagDtos(List<TagDto> tags){
    Map<Long, TagDto> parentIdChildTagMap = tags.stream().collect(Collectors.toMap(TagDto::getId, tag -> tag));
    ConcurrentHashMap<Long,TagDto> parentIdChildTag = new ConcurrentHashMap<>(parentIdChildTagMap);
    Set<Long> uniqueIdSet = parentIdChildTag.keySet();
    Iterator iterator = uniqueIdSet.iterator();
    while (iterator.hasNext()){
      TagDto tag = parentIdChildTag.get(iterator.next());
      if(tag.getParentTagId()!= null){
        TagDto parentTag = parentIdChildTag.get(tag.getParentTagId());
        if(parentTag.getChildTags() == null){
          parentTag.setChildTags(new ArrayList<>());
        }
        parentTag.getChildTags().add(tag);
        tag.setVisited(true);
      }
    }
    return new ArrayList<>(parentIdChildTag.values()).stream().filter(tag -> !tag.isVisited()).collect(Collectors.toList());
  }
}

package com.xyz.query.service;

import com.xyz.query.dto.IdNameDto;
import com.xyz.query.dto.NameDto;
import com.xyz.query.entity.PostAction;
import com.xyz.query.repository.PostActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostActionService {

  private final PostActionRepository postActionRepository;

  @Autowired
  public PostActionService(PostActionRepository postActionRepository){
    this.postActionRepository = postActionRepository;
  }

  @Cacheable("allPostActions")
  public List<IdNameDto> allPostAction(){
    return postActionRepository.getAllPostActions();
  }

  @CachePut("allPostActions")
  public IdNameDto create(NameDto postActionName){
     PostAction postAction = postActionRepository.save(PostAction.builder().name(postActionName.getName()).build());
     return IdNameDto.builder().id(postAction.getId()).name(postAction.getName()).build();
  }

}

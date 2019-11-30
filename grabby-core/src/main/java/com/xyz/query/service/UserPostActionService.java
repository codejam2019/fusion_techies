package com.xyz.query.service;

import com.xyz.query.dto.IdNameDto;
import com.xyz.query.repository.UserPostActionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserPostActionService {

  private final UserPostActionRepository postActionRepository;
  private final PostActionService postActionService;

  public UserPostActionService(UserPostActionRepository postActionRepository,PostActionService postActionService){
    this.postActionRepository = postActionRepository;
    this.postActionService = postActionService;
  }

  public void createPostAction(){

    List<IdNameDto> postActions =  postActionService.allPostAction();

    log.debug("-----------------------------------------------");
    postActions.forEach(p-> log.debug(p.toString()));
  }

}

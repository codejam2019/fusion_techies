package com.xyz.query.service;

import com.google.common.base.Strings;
import com.xyz.query.dto.PostDto;
import com.xyz.query.dto.TagDto;
import com.xyz.query.dto.request.FilterGroup;
import com.xyz.query.dto.request.PageSortFilters;
import com.xyz.query.dto.request.PostCreateDto;
import com.orientech.grabby.core.dto.response.*;
import com.xyz.query.dto.response.*;
import com.xyz.query.entity.Application;
import com.xyz.query.entity.ContentType;
import com.xyz.query.entity.Post;
import com.xyz.query.entity.Tag;
import com.xyz.query.exception.GrabbyException;
import com.xyz.query.common.util.qb.GeneratedQuery;
import com.xyz.query.common.util.qb.JPQL;
import com.xyz.query.repository.PostRepository;
import com.xyz.query.common.util.DateTimeUtility;
import com.xyz.query.common.util.Utils;
import com.xyz.query.common.util.qb.KeyValue;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostService {

  private final PostRepository postRepository;
  private final EntityManager entityManager;
  private final TagService tagService;
  private final UserPostActionService userPostActionService;

  @Autowired
  public PostService(PostRepository postRepository,EntityManager entityManager,TagService tagService,UserPostActionService userPostActionService){
    this.postRepository = postRepository;
    this.entityManager = entityManager;
    this.tagService = tagService;
    this.userPostActionService = userPostActionService;
  }

  @Transactional
  public PostDto createPost(PostCreateDto createDto, Long appId){
    //TODO server side validation
    //TODO add post owner
    if(ContentType.TEXT != createDto.getContentType()){
      throw new GrabbyException("Only Text Type post supported right now.", HttpStatus.NOT_ACCEPTABLE);
    }

    Set<Tag> tags = new HashSet<>();
    if(createDto.getTags() != null) {
      for (Long tagId : createDto.getTags()) {
        tags.add(Tag.builder().id(tagId).build());
      }
    }

    Post post = Post.builder()
                    .title(createDto.getTitle())
                    .text(Strings.isNullOrEmpty(createDto.getText()) ? null : createDto.getText().trim())
                    .richText(createDto.getRichText())
                    .creationDate(DateTimeUtility.getCurrentTimeUTC())
                    .url(createDto.getUrl())
                    .contentType(createDto.getContentType())
                    .minAge(createDto.getMinAge())
                    .isVisible(createDto.getIsVisible())
                    .maxAge(createDto.getMaxAge())
                    .tags(tags)
                    .application(Application.builder().id(appId).build())
                    .build();
    post = postRepository.save(post);
    log.debug(post.toString());

    log.info("New post created");
    return PostDto.builder()
               .id(post.getId())
               .title(post.getTitle())
               .text(post.getText())
               .contentType(post.getContentType())
               .url(post.getUrl())
               .tagIds(post.getTags()!= null
                           ? post.getTags().stream().map(Tag::getId).collect(Collectors.toSet())
                           : null)
               .creationDate(post.getCreationDate())
               .languageId(post.getLanguage()!= null ? post.getLanguage().getId() : null)
               .minAge(post.getMinAge())
               .maxAge(post.getMaxAge())
               .isVisible(post.getIsVisible())
               .appId(post.getApplication().getId())
               .build();
  }

  @Transactional
  public ResponseMessageDto deletePostById(Long id){
    if (id == null) {
      throw new GrabbyException("Post id is not provided");
    }
    Optional<Post> tag = postRepository.findById(id);
    if (!tag.isPresent()) {
      throw new GrabbyException("Post not exist with id: " + id);
    }
    postRepository.deleteById(id);
    log.info("Post Deleted");
    return ResponseMessageDto.builder().message("Post Deleted").build();
  }

  @Transactional(readOnly = true)
  @Cacheable("postPageSortFilter")
  public PageSortFilterResponse pageSortFilter(@NotNull PageSortFilters psf, Long applicationId){
    List<Filter> filters = new ArrayList<>();
    Collections.sort(filters,Comparator.comparing(Filter::getSrNo));
    filters.add(Filter.builder().srNo(0).field("appId").lvalue(applicationId).operator(Operator.EQUAL).booleanOperator(BooleanOperator.AND).build());
    List<FilterGroup> filterGroup = psf.getFilterGroup();
    filterGroup.add(0,FilterGroup.builder().booleanOperator(BooleanOperator.AND).srNo(0).filters(filters).build());
    mapFilterFieldToOriginalDbField(psf);
    Utils.initializeIfNullOrEmpty(filters);
    JPQL jpql = JPQL.builder()
                    //.field("new com.orientech.grabby.core.dto.PostDto(p.id, p.title, p.contentType, p.text, p.url, p.language.id, p.likes, p.disLikes, p.downloads, p.views, p.isVisible)")
                    .select("new com.orientech.grabby.core.dto.PostDto(p.id, p.title, p.contentType, p.text, p.url, p.language.id, p.likes, p.disLikes, p.downloads, p.views, p.isVisible, p.minAge, p.maxAge, p.creationDate )")
                    .count("COUNT(p.id)")
                    .from("Post p")
                    .where(psf.getFilters())
                    .whereGroup(filterGroup)
                    .sorting(psf.getSorting())
                    .build();
    GeneratedQuery generatedQuery = jpql.query();
    Long totalRecord = getTotalRecords(generatedQuery.getCount(),jpql.getValues());
    String queryString = generatedQuery.getSelect();
    log.debug("Query: {}",queryString);
    TypedQuery<PostDto> query;
    query = entityManager.createQuery(queryString, PostDto.class);
    Map<String, KeyValue> params = jpql.getValues();
    for(Map.Entry<String,KeyValue> entry : params.entrySet()){
      log.debug("Key: {}  Value: {} ", entry.getKey(), entry.getValue().getValue());
      if("p.contentType".equals(entry.getValue().getKey())){
        if(entry.getValue().getValue() != null){
         query.setParameter(entry.getKey(),ContentType.values()[Integer.valueOf(entry.getValue().getValue().toString())]);
        } else{
          query.setParameter(entry.getKey(),null);
        }
      } else{
        query.setParameter(entry.getKey(),entry.getValue().getValue());
      }
    }
    query.setMaxResults(psf.getRecordsPerPage());
    query.setFirstResult(psf.getRecordsPerPage() * psf.getPageNumber());

    List<PostDto> resultList = query.getResultList();
    List<Long> postIds = resultList.stream().map(PostDto::getId).collect(Collectors.toList());
    if(!Utils.isEmptyOrNull(postIds)){
      List<TagDto> tagDtos = tagService.tagDtosByPostIdsWithPostId(postIds);
      resultList.forEach(post -> {
        post.setTags(tagDtos.stream().filter(p ->post.getId().equals(p.getPostId())).collect(Collectors.toSet()));
        if(post.getTags() != null){
          post.setTagIds(post.getTags().stream().map(TagDto::getId).collect(Collectors.toSet()));
        }
      });
    }

    Pager pager = Pager.builder()
                      .filteredRecords(resultList.size())
                      .recordsPerPage(psf.getRecordsPerPage())
                      .pageNumber(psf.getPageNumber()+1)
                      .totalRecords(totalRecord)
                      .build();

    return PageSortFilterResponse.builder()
               .data(resultList)
               .pager(pager)
               .build();
  }

  private Long getTotalRecords(String queryString, Map<String,KeyValue> params){
    log.debug("Count Query: {}",queryString);
    Query query = entityManager.createQuery(queryString);
    try {
      for (Map.Entry<String, KeyValue> entry : params.entrySet()) {
        log.debug("Key: {}  Value: {} ", entry.getKey(), entry.getValue().getValue());
        if ("p.contentType".equals(entry.getValue().getKey())) {
          if (entry.getValue().getValue() != null) {
            query.setParameter(entry.getKey(), ContentType.values()[Integer.valueOf(entry.getValue().getValue().toString())]);
          } else {
            query.setParameter(entry.getKey(), null);
          }
        } else {
          query.setParameter(entry.getKey(), entry.getValue().getValue());
        }
      }
    } catch (IllegalArgumentException e){
      throw new GrabbyException(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    return  (Long) query.getSingleResult();
  }

  @Transactional
  public ResponseMessageDto likePosts(List<Long> postIds, Long applicationId){
    int updateRecordCount = postRepository.increaseLikeCountByOne(postIds,applicationId);
    return ResponseMessageDto.builder().message(updateRecordCount + " posts liked").build();
  }

  @Transactional
  public ResponseMessageDto disLikePosts(List<Long> postIds, Long applicationId){
    int updateRecordCount = postRepository.increaseDisLikeCountByOne(postIds,applicationId);
    return ResponseMessageDto.builder().message(updateRecordCount + " posts dis liked").build();
  }

  @Transactional
  public ResponseMessageDto incrementDownloadCountByOne(List<Long> postIds, Long applicationId){
    int updateRecordCount = postRepository.increaseDownloadCountByOne(postIds,applicationId);
    return ResponseMessageDto.builder().message(updateRecordCount + " posts downloaded").build();
  }

  @Transactional
  public ResponseMessageDto sharePosts(List<Long> postIds){
    int updateRecordCount = postRepository.increaseShareByOne(postIds);
    return ResponseMessageDto.builder().message(updateRecordCount + " posts shared").build();
  }

  @Transactional
  public ResponseMessageDto viewPosts(List<Long> postIds){
    int updateRecordCount = postRepository.increaseViewByOne(postIds);
    return ResponseMessageDto.builder().message(updateRecordCount + " posts viewed").build();
  }

  private void mapFilterFieldToOriginalDbField(PageSortFilters pageSortFilters){
    Map<String,String> commonToDbField = fieldToDBField();
    for( FilterGroup filterGroup : pageSortFilters.getFilterGroup()){
      List<Filter> filters = filterGroup.getFilters();
      for(Filter filter : filters){
        filter.setField(commonToDbField.get(filter.getField()));
      }
    }

    Set<Sorting> sortings = pageSortFilters.getSorting();
    for(Sorting sorting : sortings){
      sorting.setField(commonToDbField.get(sorting.getField()));
    }
  }

  private Map<String,String> fieldToDBField(){
    Map<String,String> commonToDbField = new HashMap<>();
    commonToDbField.put("postId","p.id");
    commonToDbField.put("postTitle","p.title");
    commonToDbField.put("contentType","p.contentType");
    commonToDbField.put("text","p.text");
    commonToDbField.put("url","p.url");
    commonToDbField.put("languageId","p.language.id");
    commonToDbField.put("likes","p.likes");
    commonToDbField.put("disLikes","p.disLikes");
    commonToDbField.put("downloads","p.downloads");
    commonToDbField.put("views","p.views");
    commonToDbField.put("isVisible","p.isVisible");
    commonToDbField.put("minAge","p.minAge");
    commonToDbField.put("maxAge","p.maxAge");
    commonToDbField.put("creationDate","p.creationDate");
    commonToDbField.put("ownerId","p.owner.id");
    commonToDbField.put("appId","p.application.id");
    return commonToDbField;
  }

  @Transactional
  public GenericResponse deletePostInBulk(List<Long> postIds){
   int deleteRowCount = postRepository.deletePosts(postIds);
   return GenericResponse.builder().message("Posts deleted successfully.").data(deleteRowCount).build();
  }
 }

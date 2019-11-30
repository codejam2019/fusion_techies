package com.xyz.query.common.util;

import org.springframework.data.domain.Pageable;

import java.util.*;

public final class Utils {

  private Utils(){}

  public static boolean isEmptyOrNull(Collection<?> collection){
    if(collection == null || collection.isEmpty()){
      return true;
    }
    Pageable pageable;
    return  false;
  }

  public static boolean isEmptyOrNull(Map<?,?> map){
    if(map == null || map.isEmpty()){
      return true;
    }
    Pageable pageable;
    return  false;
  }

  public static void initializeIfNullOrEmpty(List<?> list){
    if(isEmptyOrNull(list)){
      list = new ArrayList<>();
    }
  }

  public static void initializeIfNullOrEmpty(Set<?> set){
    if(isEmptyOrNull(set)){
      set = new HashSet<>();
    }
  }

  public static void initializeIfNullOrEmpty(Map<?,?> map){
    if(isEmptyOrNull(map)){
      map = new HashMap<>();
    }
  }
}

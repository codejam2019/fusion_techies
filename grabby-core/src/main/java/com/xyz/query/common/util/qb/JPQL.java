package com.xyz.query.common.util.qb;

import com.google.common.base.Strings;
import com.xyz.query.dto.request.FilterGroup;
import com.xyz.query.dto.response.Filter;
import com.xyz.query.dto.response.Operator;
import com.xyz.query.dto.response.Sorting;
import com.xyz.query.common.util.Utils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Builder
public class JPQL {

  private Set<String> selectedFields;

  @Builder.Default
  private List<Filter> where = new ArrayList<>();

  @Builder.Default
  private List<FilterGroup> whereGroup = new ArrayList<>();

  @Builder.Default
  private Set<Sorting> sorting = new HashSet<>();

  private String from;

  @Builder.Default
  private String select = "";

  @Builder.Default
  private String count = "";

  @Getter
  @Builder.Default
  private Map<String,KeyValue> values = new HashMap<>();

  public GeneratedQuery query(){

    GeneratedQuery generatedQuery = new GeneratedQuery();
    String query = "";
      //String condition = generateConditions(where);
      String condition = generateWhereConditionString(whereGroup);
      String sort = generateSorting();

      if(Strings.isNullOrEmpty(select)){
        return null;
      }

      if(!Strings.isNullOrEmpty(condition)){
        condition = " WHERE " + condition;
      }

      if(!Strings.isNullOrEmpty(sort)){
        sort = " ORDER BY " + sort;
      }
      query = "SELECT " + select +  " FROM "  + from + " " + condition + " " +  sort;
      String countQuery = "SELECT " + count +  " FROM "  + from + " " + condition;
      generatedQuery.setCount(countQuery);
      generatedQuery.setSelect(query);
       return generatedQuery;
  }

  private String generateWhereConditionString(List<FilterGroup> filterGroups){
    String conditionString ="";

    if(Utils.isEmptyOrNull(filterGroups)){
      return conditionString;
    }
    whereGroup.sort(Comparator.comparing(FilterGroup::getSrNo));
    FilterGroup fg= filterGroups.get(0);

    List<Filter> f  = fg.getFilters();
    conditionString = generateConditions(f);
    if(f.size() > 1){
      conditionString = " ( " + conditionString + ") ";
    }
    filterGroups.remove(0);
    if(!Utils.isEmptyOrNull(filterGroups)){
      for(FilterGroup filterGroup : filterGroups){
        List<Filter> filters = filterGroup.getFilters();
        String subFilter  = generateConditions(filters);
        if(filters.size() > 1){
          subFilter = " ( " + subFilter + ") ";
        }
        conditionString = conditionString + " " + filterGroup.getBooleanOperator().getValue() + " " + subFilter;
      }
    }
    return conditionString;
  }

  private String generateConditions(List<Filter> filters){
    String conditionString ="";
    if(filters == null){
      return conditionString;
    }
    List<Filter> conditionsList = filters;
    conditionsList.sort(Comparator.comparing(Filter::getSrNo));
    if(Utils.isEmptyOrNull(conditionsList)){
      return conditionString;
    }
    Filter firstCondition = conditionsList.get(0);
    conditionString = singleCondition(firstCondition);
    if(conditionsList.size() == 1){
      return  conditionString;
    }
    filters.remove(0);
    if(!Utils.isEmptyOrNull(filters)){
      for (Filter condition : filters) {
        conditionString = conditionString + condition.getBooleanOperator().getValue() + singleCondition(condition);
      }
    }
    return conditionString;
  }

  private String singleCondition(Filter condition){
    String conditionString = "";
    if(condition == null){
      return "";
    }
    String param = param();
    if(condition.getOperator() == Operator.EQUAL
    || condition.getOperator() == Operator.NOT_EQUAL
    || condition.getOperator() == Operator.GREATER_THAN
    || condition.getOperator() == Operator.GREATER_EQUAL
    || condition.getOperator() == Operator.LESS_THAN
    || condition.getOperator() == Operator.LESS_EQUAL
    || condition.getOperator() == Operator.IS){
      conditionString = " " + condition.getField() + " " + condition.getOperator().getValue() + " :" + param + " ";
      if(condition.getSvalue() != null){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getSvalue()).build());
      } else if(condition.getIvalue() != null){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getIvalue()).build());
      } else if(condition.getLvalue() != null){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getLvalue()).build());
      } else if(condition.getBValue() != null){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getBValue()).build());
      } else if(condition.getEvalue() != null){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getEvalue()).build());
      } else if(condition.getDvalue() != null){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getDvalue()).build());
      } else if(condition.getTvalue() != null){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getTvalue()).build());
      } else if(condition.getFvalue() != null){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getFvalue()).build());
      } else{
        values.put(param,KeyValue.builder().key(condition.getField()).value(null).build());
      }
    } else if(condition.getOperator() == Operator.IN || condition.getOperator() == Operator.NOT_IN){
      conditionString = " " + condition.getField() + " " +condition.getOperator().getValue() + " (:" + param + ") ";
      if(!Utils.isEmptyOrNull(condition.getSvalues())){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getSvalues()).build());
      } else if(!Utils.isEmptyOrNull(condition.getIvalues())){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getIvalues()).build());
      } else if(!Utils.isEmptyOrNull(condition.getLvalues())){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getLvalues()).build());
      } else if(!Utils.isEmptyOrNull(condition.getBvalues())){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getBvalues()).build());
      } else if(!Utils.isEmptyOrNull(condition.getEvalues())){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getEvalues()).build());
      } else if(!Utils.isEmptyOrNull(condition.getDvalues())){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getDvalues()).build());
      } else if(!Utils.isEmptyOrNull(condition.getTvalues())){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getTvalues()).build());
      } else if(!Utils.isEmptyOrNull(condition.getFvalues())){
        values.put(param,KeyValue.builder().key(condition.getField()).value(condition.getFvalues()).build());
      } else{
        values.put(param,KeyValue.builder().key(condition.getField()).value(null).build());
      }
    } else {
      return "";
    }
    return conditionString;
  }

  private String generateSorting(){
    String sortingString = "";
    if(Utils.isEmptyOrNull(sorting)){
      return sortingString;
    }
    Set<String> singleSorting = new HashSet<>();
    for(Sorting sort : sorting){
      singleSorting.add(sort.getSorting());
    }
    singleSorting.remove(null);
    singleSorting.remove("");
    sortingString = String.join(", ",singleSorting);
    return sortingString;
  }

  private String param(){
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 6;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(targetStringLength);
    for (int i = 0; i < targetStringLength; i++) {
      int randomLimitedInt = leftLimit + (int)
                                             (random.nextFloat() * (rightLimit - leftLimit + 1));
      buffer.append((char) randomLimitedInt);
    }
    String generatedString = buffer.toString();
    return generatedString;
  }
}

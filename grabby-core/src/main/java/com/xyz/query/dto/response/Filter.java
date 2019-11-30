package com.xyz.query.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Filter {

  private int srNo;

  private String field;

  @Builder.Default
  private Operator operator = Operator.EQUAL;

  private String svalue;

  private List<String> svalues;

  private Integer ivalue;

  private List<Integer> ivalues;

  private Long lvalue;

  private List<Long> lvalues;

  private Boolean bValue;

  private List<Boolean> bvalues;

  private Integer evalue;

  private List<Integer> evalues;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime tvalue;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private List<LocalDateTime> tvalues;

  private Float fvalue;

  private List<Float> fvalues;

  private Double dvalue;

  private List<Double> dvalues;

  @Builder.Default
  private BooleanOperator booleanOperator = BooleanOperator.AND;
}

package com.xyz.query.common.util.qb;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue {

  String key;

  Object value;
}

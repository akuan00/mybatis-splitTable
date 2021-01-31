package com.qinkuan.split.query;

import com.qinkuan.split.type.QueryType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by qinkuan on 2021/1/31.
 * 根据条件查询
 */
@Getter
@Setter
@Builder
public class CriteriaQuery {

    private String key;
    private Object value;
    private QueryType queryType;


}

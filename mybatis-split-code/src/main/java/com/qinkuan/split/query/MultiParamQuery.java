package com.qinkuan.split.query;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinkuan on 2021/1/31.
 * 多个查询条件
 */
@Getter
@Setter
public class MultiParamQuery {

    private List<CriteriaQuery> queries;

    public MultiParamQuery and(CriteriaQuery query){
        if (queries == null){
            queries = new ArrayList<>();
        }
        queries.add(query);
        return this;
    }

}

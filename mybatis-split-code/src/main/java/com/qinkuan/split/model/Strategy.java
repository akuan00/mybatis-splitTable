package com.qinkuan.split.model;

import com.qinkuan.split.type.SplitType;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by qinkuan on 2020/12/31.
 * 分表策略
 */
@Getter
@Setter
public class Strategy {

    private String tableName;

    private SplitType type;

    private long startId;
    private long endId;



}

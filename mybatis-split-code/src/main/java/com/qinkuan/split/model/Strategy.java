package com.qinkuan.split.model;

import com.qinkuan.split.type.SplitType;
import com.qinkuan.split.util.DateUtil;
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

    /**
     *  hash 分表数量，rang 步长
     */
    private long count;
    /**
     *  日期格式 yyyyMMdd
     */
    private String formatStr;

    /**
     * 默认策略
     * @param type
     * @return
     */
    public static Strategy getDefult(SplitType type){
        Strategy strategy = new Strategy();
        if (type == SplitType.DATE){
            strategy.setFormatStr(DateUtil.DATE2);
        }else if (type == SplitType.ID_HASH){
            strategy.setCount(10);
        }else if (type == SplitType.ID_RANG){
            strategy.setCount(50000000);
        }
        return strategy;
    }

}

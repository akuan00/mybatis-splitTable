package com.qinkuan.split;

import com.qinkuan.split.model.Strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qinkuan on 2020/12/31.
 * 分表规则
 */
public class SplitTables {
    private static Map<String,Strategy> splitStrategyMap = new HashMap<>();

    public static void setSplitStrategyMap(Map<String, Strategy> splitStrategyMap) {
        SplitTables.splitStrategyMap = splitStrategyMap;
    }

    public static void putStrategy(String tableName,Strategy strategy){
        splitStrategyMap.put(tableName,strategy);
    }

    public static  Strategy getSplitStrategy(String key) {
        return splitStrategyMap.get(key);
    }

}

package com.qinkuan.split.demo.config;

import com.qinkuan.split.SplitTables;
import com.qinkuan.split.model.Strategy;
import com.qinkuan.split.type.SplitType;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by qinkuan on 2021/1/28.
 * 自定义分表策略
 */
@Configuration
public class SplitConfig {

    @PostConstruct
    public void init(){
        Strategy userStrategy = new Strategy();
        userStrategy.setTableName("user_info");
        userStrategy.setType(SplitType.ID_RANG);
        userStrategy.setCount(10);
        SplitTables.putStrategy(userStrategy.getTableName(),userStrategy);
    }
}

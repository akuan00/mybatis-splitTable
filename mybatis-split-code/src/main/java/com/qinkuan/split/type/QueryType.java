package com.qinkuan.split.type;

/**
 * Created by qinkuan on 2021/1/31.
 * 查询
 */
public enum QueryType {
    EQ("="),NOT_EQ("!="),
    lessThan("<"),lessThanAndEq("<="),greaterThan(">"),greaterThanAndEq(">="),
    like(" like ")
    ;

    private String value;

     QueryType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

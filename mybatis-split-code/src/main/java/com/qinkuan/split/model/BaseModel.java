package com.qinkuan.split.model;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by qinkuan on 2020/12/25
 * 所有数据库模型的父类
 */
@Getter
@Setter
public abstract class BaseModel implements Serializable {

    private Date createTime;

    private Date updateTime;

}

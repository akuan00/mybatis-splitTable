package com.qinkuan.split.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by qinkuan on 2020/12/25
 * id自增
 */
@Getter
@Setter
public abstract class BaseIdModel extends BaseModel {

    private Long id;


}

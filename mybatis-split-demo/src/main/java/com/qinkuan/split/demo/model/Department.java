package com.qinkuan.split.demo.model;

import com.qinkuan.split.annotation.Table;
import com.qinkuan.split.model.BaseIdModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by qinkuan on 2020/12/31.
 * 部门 ID自增
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
public class Department extends BaseIdModel {

    private String name;

}

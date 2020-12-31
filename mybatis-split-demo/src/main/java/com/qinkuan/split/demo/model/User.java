package com.qinkuan.split.demo.model;

import com.qinkuan.split.annotation.Table;
import com.qinkuan.split.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by qinkuan on 2020/12/31.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")
public class User   extends BaseModel {

    long id;

    int sex;
    String mobileNo; // 手机号
    String email;
    String icon; //用户头像

    int departmentId;
}

package com.qinkuan.split.demo.mapper;

import com.qinkuan.split.demo.model.User;
import com.qinkuan.split.mapper.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends IMapper<User, Long> {


    @Select(value = "select u.*,d.name as department_name from ${tableName} as u left join department as d on u.department_id=d.id")
    List<User> findByJoin(@Param("tableName") String tableName);


}

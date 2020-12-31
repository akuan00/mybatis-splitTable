package com.qinkuan.split.demo.mapper;

import com.qinkuan.split.demo.model.User;
import com.qinkuan.split.mapper.IMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserMapper extends IMapper<User, Long> {


    @Select(value = "select * from ${tableName} where id >= #{startId} limit #{size}")
    List<User> findByIdAndSize(@Param("tableName") String tableName, @Param("startId") long startId, @Param("size") int size);

    @Cacheable(cacheNames = "commonCache", key = "'user:' + #tableName + ':' +#type")
    @Select(value = "select id from ${tableName} where `type`=#{type} and sex = 0 limit #{limit}")
    List<Long> getUserIds(@Param("tableName") String tableName, @Param("type") int type, @Param("limit") int limit);
}

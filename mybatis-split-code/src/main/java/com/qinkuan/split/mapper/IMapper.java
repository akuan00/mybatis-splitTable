package com.qinkuan.split.mapper;

import com.qinkuan.split.model.BaseModel;
import com.qinkuan.split.provider.SqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by qinkuan on 2020/11/25
 * 所有mapper 的父类
 */

public interface IMapper<T extends BaseModel, ID extends Serializable>  {


    @Select(value = "${sql}")
    List<T> page(@Param("sql") String sql,@Param("wheres") Map<String, Object> wheres);

    @Select(value = "${sql}")
    long count(@Param("sql") String sql,@Param("wheres") Map<String, Object> wheres);

    @Select(value = "select * from ${tableName} where id=#{id}")
    T findById(@Param("tableName") String tableName, @Param("id") ID id);

    @SelectProvider(type = SqlProvider.class,method = "findOne")
    T findOne(@Param("tableName") String tableName, @Param("wheres") Map<String, Object> wheres);

    @Transactional
    @InsertProvider(type = SqlProvider.class,method = "insert")
    void save(@Param("tableName") String tableName, @Param("t") T t);

    /**
     * id自增保存
     * @param tableName
     * @param t
     */
    @Transactional
    @InsertProvider(type = SqlProvider.class,method = "insert")
    @Options(useGeneratedKeys=true,keyProperty = "t.id",keyColumn = "t.id")
    void saveAutoKey(@Param("tableName") String tableName, @Param("t") T t);

    /**
     * 修改全部字段
     * @param tableName
     * @param t
     */
    @Transactional
    @UpdateProvider(type = SqlProvider.class,method = "update")
    void update(@Param("tableName") String tableName, @Param("t") T t);

    @Transactional
    @Delete(value = "delete from ${tableName} where  id=#{id}")
    Integer deleteById(@Param("tableName") String tableName, @Param("id") ID id);

    /**
     * 局部修改
     * @param tableName
     * @param sets
     * @param wheres
     * @return
     */
    @Transactional
    @UpdateProvider(type = SqlProvider.class,method = "updateSelective")
    Integer updateSelective(@Param("tableName") String tableName, @Param("sets") Map<String, Object> sets, @Param("wheres") Map<String, Object> wheres);


}

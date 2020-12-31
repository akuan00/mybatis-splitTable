package com.qinkuan.split.service;

import com.qinkuan.split.annotation.Table;
import com.qinkuan.split.mapper.IMapper;
import com.qinkuan.split.model.BaseModel;
import com.qinkuan.split.model.Pager;
import com.qinkuan.split.provider.SqlProvider;
import org.apache.ibatis.jdbc.SQL;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author: qinkuan
 * @date: 2020/12/7 21:52.
 * 不需要分表的继承此类
 */
public abstract class AbstractMapperService<T extends BaseModel, ID extends Serializable> {

    protected Class classz = (Class)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    /**
     * 使用缓存的命名空间
     * @return
     */
    protected abstract String cacheNamespace();

    protected abstract IMapper getMapper();

    /**
     * 获取表名
     * @return
     */
    protected  String getTableName(){
         if (classz.isAnnotationPresent(Table.class)){
             Table table = (Table)classz.getAnnotation(Table.class);
             return table.name();
         }
         return SqlProvider.humpToLine(classz.getSimpleName());
    }


    public T findOneByParam(Map<String,Object> wheres){
        return   (T)getMapper().findOneByParam(getTableName(),wheres);
    }

    public  List<T> findMultiByParam( Map<String,Object> wheres,int limit){
        return (List<T>) getMapper().findMultiByParam(getTableName(),wheres,limit);
    }

    public T get(ID id) {
        return findById(id);
    }

    public T findById(ID id) {
        return (T)getMapper().findById(getTableName(),id);
    }

    public T save(T t) {
        getMapper().save(getTableName(),t);
        return t;
    }

    public T update(T t) {
         getMapper().update(getTableName(),t);
         return t;
    }



    public Integer deleteById(ID id) {
       return getMapper().deleteById(getTableName(),id);
    }



    public List<T> saveAll(List<T> list) {
        list.forEach(it->{
            getMapper().save(getTableName(),it);
        });
        return list;
    }

    public Pager<T> page(Pager<T> pager){
        SQL sql = new SQL();
        String tName = getTableName();

        sql.SELECT("*").FROM(tName);
        addQueryParams(sql,pager.getParams());
        List<String> orders = pager.getOrders();
        if (orders != null && orders.size()>0){
            orders.forEach(it->{
                sql.ORDER_BY(SqlProvider.humpToLine(it.replace("|"," ")));
            });
        }
        sql.OFFSET(pager.getOffset());
        sql.LIMIT(pager.getSize());
        List<T> list = getMapper().page(sql.toString(),pager.getParams());
        pager.setList(list);
        return pager;
    }


    protected void addQueryParams(SQL sql, Map<String, Object> params) {
        if (params!=null && params.size() > 0){
            params.keySet().forEach(it->{
                sql.WHERE(SqlProvider.humpToLine(it)+"=#{wheres["+it+"]}");
            });
        }
    }

}

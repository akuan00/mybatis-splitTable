package com.qinkuan.split.service;

import com.qinkuan.split.mapper.IMapper;
import com.qinkuan.split.model.BaseModel;
import com.qinkuan.split.provider.SqlProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: qinkuan
 * @date: 2020/12/7 21:52.
 * 需要分表的继承此类
 */
public abstract class AbstractSplitMapperService<T extends BaseModel, ID extends Serializable> {


    protected abstract String getTableName(ID id);
    protected abstract String[] getAllTableName();
    protected abstract IMapper getMapper();


    public T findOneByParam(Map<String,Object> wheres){
        String[] tableNames = getAllTableName();
        T t = null;
        for (int i = 0; i < tableNames.length; i++) {
           t = (T)getMapper().findOneByParam(tableNames[i],wheres);
           if (t != null){
               return t;
           }
        }
        return null;
    }

    public  List<T> findMultiByParam( Map<String,Object> wheres,int limit){
        String[] tableNames = getAllTableName();
       List<T> list = new ArrayList<T>();
        for (int i = 0; i < tableNames.length; i++) {
            List<T>  l = (List<T>) getMapper().findMultiByParam(tableNames[i],wheres,limit);
            if (l != null && l.size()>0){
                list.addAll(l);
                if (list.size()>=limit){
                    break;
                }
            }
        }
        return list;
    }



    public T get(ID id) {
        return findById(id);
    }
    public T findById(ID id) {
        return (T)getMapper().findById(getTableName(id),id);
    }

    public T save(T t) {
        getMapper().save(getTableName((ID) SqlProvider.getFieldID(t)),t);
        return t;
    }


    public T update(T t) {
         getMapper().update(getTableName((ID) SqlProvider.getFieldID(t)),t);
         return t;
    }



    public Integer deleteById(ID id) {
       return getMapper().deleteById(getTableName(id),id);
    }



    public List<T> saveAll(List<T> list) {
        list.forEach(it->{
            getMapper().save(getTableName((ID) SqlProvider.getFieldID(it)),it);
        });
        return list;
    }



}

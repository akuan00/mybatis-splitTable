package com.qinkuan.split.service;

import com.qinkuan.split.SplitTables;
import com.qinkuan.split.annotation.Table;
import com.qinkuan.split.mapper.IMapper;
import com.qinkuan.split.model.BaseModel;
import com.qinkuan.split.model.Pager;
import com.qinkuan.split.model.Strategy;
import com.qinkuan.split.provider.SqlProvider;
import com.qinkuan.split.type.SplitType;
import com.qinkuan.split.util.DateUtil;
import org.apache.ibatis.jdbc.SQL;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author: qinkuan
 * @date: 2020/12/7 21:52.
 * 数据库操作Service继承此类
 */
public abstract class AbstractMapperService<T extends BaseModel, ID extends Serializable> {

    private static final String separator = "_";

    protected Class classz = (Class)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    /**
     * 使用缓存的命名空间
     * @return
     */
    protected abstract String cacheNamespace();

    protected abstract IMapper getMapper();
    /**
     * 分表策略
     * @return
     */
    protected abstract SplitType getSplitType();

    /**
     * 获取表名
     * @return
     */
    protected  String getTableName(ID id){
        String tableName = null;
        if (classz.isAnnotationPresent(Table.class)){
             Table table = (Table)classz.getAnnotation(Table.class);
             tableName = table.name();
         }else {
             tableName = SqlProvider.humpToLine(classz.getSimpleName());
         }
         if (getSplitType() != null) {
             Strategy splitStrategy = SplitTables.getSplitStrategy(tableName);
             if (splitStrategy == null){
                 splitStrategy = Strategy.getDefult(getSplitType());
             }
             if (getSplitType() == SplitType.DATE) {
                tableName += (separator + DateUtil.format(DateUtil.nowDate(),splitStrategy.getFormatStr()));
             } else if (getSplitType() == SplitType.ID_RANG) {
                 long count = splitStrategy.getCount();
                 Long nowId = (Long) id ;
                 tableName += (separator+ (nowId / count));
             } else if (getSplitType() == SplitType.ID_HASH) {
                 long count = splitStrategy.getCount();
                 tableName += (separator + ((Long)id % count));
             }
         }
        System.out.println(" tableName================"+tableName);
        return tableName;
    }


    public T findOne(Map<String,Object> wheres){
        String[] tableNames = null;//getAllTableName()
        T t = null;
        for (int i = 0; i < tableNames.length; i++) {
            t = (T)getMapper().findOne(tableNames[i],wheres);
            if (t != null){
                return t;
            }
        }
        return null;
    }


    public T get(ID id) {
        return findById(id);
    }

    public T findById(ID id) {
        return (T)getMapper().findById(getTableName(id),id);
    }

    /**
     * ID自增
     * @param t
     * @return
     */
    public T saveAutoKey(T t) {
        getMapper().saveAutoKey(getTableName(null),t);
        return t;
    }

    /**
     * 指定ID
     * @param t
     * @return
     */
    public T save(T t) {
        getMapper().save(getTableName((ID) SqlProvider.getFieldID(t)),t);
        return t;
    }

    public T update(T t) {
        getMapper().update(getTableName((ID) SqlProvider.getFieldID(t)),t);
        return t;
    }

    public int updateSelective(ID id,Map<String, Object> sets, Map<String, Object> wheres) {
        return getMapper().updateSelective(getTableName(id),sets,wheres);
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

    public Pager<T> page(Pager<T> pager){
        SQL sql = new SQL();
        String tName = getTableName(null);

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

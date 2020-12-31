package com.qinkuan.split.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by qinkuan on 2020/4/25
 */
public class Pager<T> implements Serializable {

    private int page = 0;
    private int size = 30;
    private long total;
    private Map<String, Object> params;
    private List<T> list;
    private List<String> orders;

    public Pager(){}
    public Pager(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getOffset() {
        return page * size;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        return total % size == 0 ? total / size : total / size + 1;
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }




    @Override
    public String toString() {
        return "Pager{" +
                "page=" + page +
                ", size=" + size +
                ", total=" + total +
                ", params=" + params +
                ", list=" + list +
                ", orders=" + orders +
                '}';
    }
}

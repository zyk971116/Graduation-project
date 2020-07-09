package com.jf.school.bean;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageBean<T> {

    long total;
    List<T> rows;

    public PageBean() {
    }

    public PageBean(List<T> datas) {
        PageInfo<T> pageInfo = new PageInfo<>(datas);
        this.total = pageInfo.getTotal();
        // 可以直接用datas
        this.rows = pageInfo.getList();
    }

    public PageBean(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}

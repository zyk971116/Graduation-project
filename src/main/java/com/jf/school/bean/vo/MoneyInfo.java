package com.jf.school.bean.vo;

import com.jf.school.bean.table.Article;
import com.jf.school.bean.table.InHouse;

import java.util.List;

/**
 * 封装归档信息
 */
public class MoneyInfo {

    private String date;
    private Double count;
    private List<InHouse> inHouses;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }


    public List<InHouse> getInHouses() {
        return inHouses;
    }

    public void setInHouses(List<InHouse> inHouses) {
        this.inHouses = inHouses;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "date='" + date + '\'' +
                ", count='" + count + '\'' +
                ", InHouses=" + inHouses +
                '}';
    }
}

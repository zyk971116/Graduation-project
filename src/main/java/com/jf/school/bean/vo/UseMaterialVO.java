package com.jf.school.bean.vo;

import com.jf.school.bean.table.Article;
import com.jf.school.bean.table.UseMaterialInfo;

import java.util.List;

/**
 * 封装归档信息
 */
public class UseMaterialVO {

    private String date;

    private List<UseMaterialInfo> useMaterialInfo;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public List<UseMaterialInfo> getUseMaterialInfo() {
        return useMaterialInfo;
    }

    public void setUseMaterialInfo(List<UseMaterialInfo> useMaterialInfo) {
        this.useMaterialInfo = useMaterialInfo;
    }
}

package com.jf.school.bean.table;

import javax.persistence.Table;

@Table(name = "user_role")
public class UserTitle {

    private Integer id;
    private String  fkUserId ;
    private Integer fkTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId;
    }

    public Integer getFkTitle() {
        return fkTitle;
    }

    public void setFkTitle(Integer fkTitle) {
        this.fkTitle = fkTitle;
    }
}

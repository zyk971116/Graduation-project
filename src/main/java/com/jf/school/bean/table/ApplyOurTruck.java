package com.jf.school.bean.table;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="apply_our_truck")
public class ApplyOurTruck {
    @Id
    private Integer id;
    private Integer fkApply;
    private Integer fkOurTruck;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkApply() {
        return fkApply;
    }

    public void setFkApply(Integer fkApply) {
        this.fkApply = fkApply;
    }

    public Integer getFkOurTruck() {
        return fkOurTruck;
    }

    public void setFkOurTruck(Integer fkOurTruck) {
        this.fkOurTruck = fkOurTruck;
    }
}

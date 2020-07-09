package com.jf.school.bean.table;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="orders_truck")
public class OrderTruck {
    @Id
    private Integer id;
    private Integer fkOrder;
    private Integer fkTruck;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkOrder() {
        return fkOrder;
    }

    public void setFkOrder(Integer fkOrder) {
        this.fkOrder = fkOrder;
    }

    public Integer getFkTruck() {
        return fkTruck;
    }

    public void setFkTruck(Integer fkTruck) {
        this.fkTruck = fkTruck;
    }
}

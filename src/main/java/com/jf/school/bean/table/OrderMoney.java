package com.jf.school.bean.table;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "order_money")
public class OrderMoney {

    @Id
    private Integer id;
    private Integer fkOrder;
    private Double midMoney;
    private Double falseMoney;

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

    public Double getMidMoney() {
        return midMoney;
    }

    public void setMidMoney(Double midMoney) {
        this.midMoney = midMoney;
    }


    public Double getFalseMoney() {
        return falseMoney;
    }

    public void setFalseMoney(Double falseMoney) {
        this.falseMoney = falseMoney;
    }
}

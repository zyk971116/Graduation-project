package com.jf.school.bean.table;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "money")
public class Money {

    private Double ourMoney;
    private Double otherMoney;

    public Double getOurMoney() {
        return ourMoney;
    }

    public void setOurMoney(Double ourMoney) {
        this.ourMoney = ourMoney;
    }

    public Double getOtherMoney() {
        return otherMoney;
    }

    public void setOtherMoney(Double otherMoney) {
        this.otherMoney = otherMoney;
    }
}

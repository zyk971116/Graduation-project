package com.jf.school.bean.vo;

import com.jf.school.bean.table.Business;
import com.jf.school.bean.table.Admin;

public class InHouseVO {
    private Integer id;
    private Integer orderNumber;
    private String name;
    private String category;
    private String quality;
    private Double price;
    private Integer total;
    private Double totalPrice;
    private Long created;
    private Integer state;
    private Integer state1;
    private Integer inTotal;
    private Double inPrice;

    private Business company;
    private Admin user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getInTotal() {
        return inTotal;
    }

    public void setInTotal(Integer inTotal) {
        this.inTotal = inTotal;
    }

    public Business getCompany() {
        return company;
    }

    public void setCompany(Business company) {
        this.company = company;
    }

    public Admin getUser() {
        return user;
    }

    public void setUser(Admin user) {
        this.user = user;
    }

    public Double getInPrice() {
        return inPrice;
    }

    public void setInPrice(Double inPrice) {
        this.inPrice = inPrice;
    }

    public Integer getState1() {
        return state1;
    }

    public void setState1(Integer state1) {
        this.state1 = state1;
    }
}

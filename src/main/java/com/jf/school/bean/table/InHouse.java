package com.jf.school.bean.table;


import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "in_house")
public class InHouse {
    @Id
    private Integer id;
    private Integer orderNumber;
    private String name;
    private String category;
    private String quality;
    private Double price;
    private Integer total;
    private Double totalPrice;
    private String fkCompany;
    private String fkUser;
    private Long created;
    private Integer state;
    private Integer state1;
    private Integer inTotal;
    private Double inPrice;


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

    public String getFkCompany() {
        return fkCompany;
    }

    public void setFkCompany(String fkCompany) {
        this.fkCompany = fkCompany;
    }

    public String getFkUser() {
        return fkUser;
    }

    public void setFkUser(String fkUser) {
        this.fkUser = fkUser;
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

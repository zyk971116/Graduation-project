package com.jf.school.bean.table;


import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "out_house")
public class OutHouse {
    @Id
    private Integer id;
    private Integer orderNumber;
    private String name;
    private String category;
    private String quality;
    private Integer total;
    private Integer fkUser;
    private Long created;
    private Integer state;
    private Integer fkAdmin;


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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
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

    public Integer getFkAdmin() {
        return fkAdmin;
    }

    public void setFkAdmin(Integer fkAdmin) {
        this.fkAdmin = fkAdmin;
    }
}

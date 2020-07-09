package com.jf.school.bean.table;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="business")
public class Business {
    @Id
    private Integer id;
    private String name;
    private String address;
    private String personName;
    private Long tel;
    private Float defectiveRate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public Float getDefectiveRate() {
        return defectiveRate;
    }

    public void setDefectiveRate(Float defectiveRate) {
        this.defectiveRate = defectiveRate;
    }
}

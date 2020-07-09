package com.jf.school.bean.vo;

import com.jf.school.bean.table.Archive;
import com.jf.school.bean.table.Article;
import com.jf.school.bean.table.UseMaterialInfo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

public class Home {


    private Integer orderTotal;
    private Integer applyTotal;
    private Integer noticeTotal;
    private List<Archive> archive;
    private List<Article> article;
    private List<UseMaterialVO> materialVOS;
    private BigDecimal one;
    private BigDecimal two;
    private BigDecimal three;

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Integer getApplyTotal() {
        return applyTotal;
    }

    public void setApplyTotal(Integer applyTotal) {
        this.applyTotal = applyTotal;
    }

    public Integer getNoticeTotal() {
        return noticeTotal;
    }

    public void setNoticeTotal(Integer noticeTotal) {
        this.noticeTotal = noticeTotal;
    }

    public List<Archive> getArchive() {
        return archive;
    }

    public void setArchive(List<Archive> archive) {
        this.archive = archive;
    }

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }

    public BigDecimal getOne() {
        return one;
    }

    public void setOne(BigDecimal one) {
        this.one = one;
    }

    public BigDecimal getTwo() {
        return two;
    }

    public void setTwo(BigDecimal two) {
        this.two = two;
    }

    public BigDecimal getThree() {
        return three;
    }

    public void setThree(BigDecimal three) {
        this.three = three;
    }

    public List<UseMaterialVO> getMaterialVOS() {
        return materialVOS;
    }

    public void setMaterialVOS(List<UseMaterialVO> materialVOS) {
        this.materialVOS = materialVOS;
    }
}

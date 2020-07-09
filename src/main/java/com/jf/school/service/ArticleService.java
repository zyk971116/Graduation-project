package com.jf.school.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.school.bean.PageBean;
import com.jf.school.bean.Result;
import com.jf.school.bean.table.Apply;
import com.jf.school.bean.table.ApplyOurTruck;
import com.jf.school.bean.table.Article;
import com.jf.school.bean.table.OurTruck;
import com.jf.school.mapper.ApplyOurTruckMapper;
import com.jf.school.mapper.ArticleMapper;
import com.jf.school.mapper.OurTruckMapper;
import com.jf.school.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {

    @Resource
    ArticleMapper articleMapper;

    public Result add(Article article) {

        article.setCreated(DateUtils.getUnixTimeStamp());
        if (article.getId() == null){
            articleMapper.insertSelective(article);
            return new Result(Result.CODE_SUCCESS, "添加成功");
        }else {
            articleMapper.updateByPrimaryKey(article);
            return new Result(Result.CODE_SUCCESS, "修改成功");
        }
    }

    public Result list(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Article> articles = articleMapper.selectAll();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        PageBean pageBean = new PageBean();
        pageBean.setRows(articles);
        pageBean.setTotal(pageInfo.getTotal());
        return new Result(Result.CODE_SUCCESS, "查询成功",pageBean);
    }

    public Result findById(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return new Result(Result.CODE_SUCCESS, "查询成功",article);
    }
}

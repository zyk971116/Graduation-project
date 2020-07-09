package com.jf.school.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.school.bean.PageBean;
import com.jf.school.bean.Result;
import com.jf.school.bean.table.Business;
import com.jf.school.bean.table.OurMaterial;
import com.jf.school.mapper.BusinessMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BusinessService {

    @Resource
    BusinessMapper businessMapper;


    //获取全部厂家信息列表
    public Result list(Integer currentPage, Integer pageSize, String name) {
        PageHelper.startPage(currentPage, pageSize);

        Example example=new Example(Business.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");

        List<Business> businesses = businessMapper.selectByExample(example);
        // 获取分页信息
        PageInfo<Business> pageInfo = new PageInfo<>(businesses);

        PageBean pageBean = new PageBean();
        pageBean.setRows(businesses);
        pageBean.setTotal(pageInfo.getTotal());
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);
    }

    public Result list() {
        List<Business> businesses = businessMapper.selectAll();
        return new Result(Result.CODE_SUCCESS, "查询成功", businesses);
    }
    //添加厂家信息
    public Result addBusiness(Business business) {
        int count = businessMapper.insertSelective(business);
        return new Result(Result.CODE_SUCCESS, "添加成功");

    }

    //编辑信息回显
    public Result get(Integer id) {
        Business business = businessMapper.selectByPrimaryKey(id);
        return new Result(Result.CODE_SUCCESS, "获取成功", business);
    }
    //更新
    public Result updateById(Business business) {
        int count = businessMapper.updateByPrimaryKeySelective(business);
        return new Result(Result.CODE_SUCCESS, "更新成功");
    }

    public Result getDefectiveRate(Integer id) {
        Business business = businessMapper.selectByPrimaryKey(id);
        return new Result(Result.CODE_SUCCESS, "获取成功", business.getDefectiveRate());
    }
}

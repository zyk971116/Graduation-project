package com.jf.school.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.school.bean.PageBean;
import com.jf.school.bean.Result;
import com.jf.school.bean.table.OurTruck;
import com.jf.school.bean.table.Truck;
import com.jf.school.mapper.OurTruckMapper;
import com.jf.school.mapper.TruckMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OurTruckService {

    @Resource
    OurTruckMapper ourTruckMapper;


    //获取全部货车信息列表
    public Result list(Integer currentPage, Integer pageSize, String carNumber) {
        PageHelper.startPage(currentPage, pageSize);

        Example example=new Example(OurTruck.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("carNumber","%"+carNumber+"%");

        List<OurTruck> trucks = ourTruckMapper.selectByExample(example);
        // 获取分页信息
        PageInfo<OurTruck> pageInfo = new PageInfo<>(trucks);

        PageBean pageBean = new PageBean();
        pageBean.setRows(trucks);
        pageBean.setTotal(pageInfo.getTotal());
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);
    }


    //添加货车信息
    public Result addTruck(OurTruck ourTruck) {
        ourTruck.setState(0);
        int count = ourTruckMapper.insertSelective(ourTruck);
        return new Result(Result.CODE_SUCCESS, "添加成功");

    }

    //编辑信息回显
    public Result get(Integer id) {
        OurTruck ourTruck = ourTruckMapper.selectByPrimaryKey(id);
        return new Result(Result.CODE_SUCCESS, "获取成功", ourTruck);
    }
    //更新
    public Result updateById(OurTruck ourTruck) {
        int count = ourTruckMapper.updateByPrimaryKeySelective(ourTruck);
        return new Result(Result.CODE_SUCCESS, "更新成功");
    }

    public Result getTruckList() {
        Example example=new Example(OurTruck.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("state",0);

        List<OurTruck> trucks = ourTruckMapper.selectByExample(example);
        return new Result(Result.CODE_SUCCESS, "查询成功",trucks);
    }

    public Result getTruckByApplyId(Integer applyId) {
        List<OurTruck> truck=ourTruckMapper.getTruckByApplyId(applyId);

        return new Result(Result.CODE_SUCCESS, "查询成功",truck);
    }
}

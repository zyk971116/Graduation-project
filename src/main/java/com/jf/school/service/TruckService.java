package com.jf.school.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.school.bean.PageBean;
import com.jf.school.bean.Result;
import com.jf.school.bean.table.Business;
import com.jf.school.bean.table.OurMaterial;
import com.jf.school.bean.table.Truck;
import com.jf.school.mapper.BusinessMapper;
import com.jf.school.mapper.OrderTruckMapper;
import com.jf.school.mapper.TruckMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TruckService {

    @Resource
    TruckMapper truckMapper;


    //获取全部货车信息列表
    public Result list(Integer currentPage, Integer pageSize, String carNumber) {
        PageHelper.startPage(currentPage, pageSize);

        Example example=new Example(Truck.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("carNumber","%"+carNumber+"%");

        List<Truck> trucks = truckMapper.selectByExample(example);
        // 获取分页信息
        PageInfo<Truck> pageInfo = new PageInfo<>(trucks);

        PageBean pageBean = new PageBean();
        pageBean.setRows(trucks);
        pageBean.setTotal(pageInfo.getTotal());
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);
    }


    //添加货车信息
    public Result addTruck(Truck truck) {
        truck.setState(0);
        int count = truckMapper.insertSelective(truck);
        return new Result(Result.CODE_SUCCESS, "添加成功");

    }

    //编辑信息回显
    public Result get(Integer id) {
        Truck truck = truckMapper.selectByPrimaryKey(id);
        return new Result(Result.CODE_SUCCESS, "获取成功", truck);
    }
    //更新
    public Result updateById(Truck truck) {
        int count = truckMapper.updateByPrimaryKeySelective(truck);
        return new Result(Result.CODE_SUCCESS, "更新成功");
    }

    public Result getTruckList() {
        Example example=new Example(Truck.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("state",0);

        List<Truck> trucks = truckMapper.selectByExample(example);
        return new Result(Result.CODE_SUCCESS, "查询成功",trucks);
    }

    public Result getTruckByOrderId(Integer orderId) {
        List<Truck> truck=truckMapper.getTruckByOrderId(orderId);

        return new Result(Result.CODE_SUCCESS, "查询成功",truck);
    }
}

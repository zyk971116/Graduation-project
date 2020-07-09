package com.jf.school.service;


import com.jf.school.bean.Result;
import com.jf.school.bean.table.OrderTruck;
import com.jf.school.bean.table.Truck;
import com.jf.school.mapper.OrderTruckMapper;
import com.jf.school.mapper.TruckMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class OrderTruckService {

    @Resource
    OrderTruckMapper orderTruckMapper;

    @Resource
    TruckMapper truckMapper;

    public Result addTruckToOrder(Integer orderId,Integer truckId) {
        OrderTruck orderTruck=new OrderTruck();
        orderTruck.setFkOrder(orderId);
        orderTruck.setFkTruck(truckId);

        Truck truck=new Truck();
        truck.setId(truckId);
        truck.setState(1);
        truckMapper.updateByPrimaryKeySelective(truck);

        int count = orderTruckMapper.insertSelective(orderTruck);
        return new Result(Result.CODE_SUCCESS, " 设置成功");


    }
}

package com.jf.school.service;


import com.jf.school.bean.Result;
import com.jf.school.bean.table.ApplyOurTruck;
import com.jf.school.bean.table.OrderTruck;
import com.jf.school.bean.table.OurTruck;
import com.jf.school.bean.table.Truck;
import com.jf.school.mapper.ApplyOurTruckMapper;
import com.jf.school.mapper.OrderTruckMapper;
import com.jf.school.mapper.OurTruckMapper;
import com.jf.school.mapper.TruckMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApplyOurTruckService {

    @Resource
    ApplyOurTruckMapper applyOurTruckMapper;

    @Resource
    OurTruckMapper ourTruckMapper;

    public Result addTruckToApply(Integer applyId,Integer truckId) {
        ApplyOurTruck applyOurTruck=new ApplyOurTruck();
        applyOurTruck.setFkApply(applyId);
        applyOurTruck.setFkOurTruck(truckId);

        OurTruck truck=new OurTruck();
        truck.setId(truckId);
        truck.setState(1);
        ourTruckMapper.updateByPrimaryKeySelective(truck);

        int count = applyOurTruckMapper.insertSelective(applyOurTruck);
        return new Result(Result.CODE_SUCCESS, " 设置成功");


    }
}

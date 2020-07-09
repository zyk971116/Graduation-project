package com.jf.school.mapper;

import com.jf.school.bean.table.Truck;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface TruckMapper extends Mapper<Truck> {


    List<Truck> getTruckByOrderId(@Param("orderId") Integer orderId);
}

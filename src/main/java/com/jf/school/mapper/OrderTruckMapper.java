package com.jf.school.mapper;


import com.jf.school.bean.table.OrderTruck;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface OrderTruckMapper extends Mapper<OrderTruck> {

    List<Integer> selectByOrderId(Integer id);


    void deleteByOrderId(@Param("orderId") Integer orderId);
}

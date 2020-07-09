package com.jf.school.mapper;


import com.jf.school.bean.table.ApplyOurTruck;
import com.jf.school.bean.table.OrderTruck;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface ApplyOurTruckMapper extends Mapper<ApplyOurTruck> {

    List<Integer> selectByApplyId(@Param("applyId")Integer applyId);


    void deleteByOrderId(@Param("orderId") Integer orderId);
}

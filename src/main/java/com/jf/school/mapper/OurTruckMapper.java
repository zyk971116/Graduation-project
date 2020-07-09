package com.jf.school.mapper;

import com.jf.school.bean.table.OurTruck;
import com.jf.school.bean.table.Truck;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface OurTruckMapper extends Mapper<OurTruck> {


    List<OurTruck> getTruckByApplyId(@Param("applyId") Integer applyId);
}

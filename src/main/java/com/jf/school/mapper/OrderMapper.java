package com.jf.school.mapper;


import com.jf.school.bean.table.Order;
import tk.mybatis.mapper.common.Mapper;


public interface OrderMapper extends Mapper<Order> {


    Integer getLastOrderNumber();

    Integer selectTotal();
}

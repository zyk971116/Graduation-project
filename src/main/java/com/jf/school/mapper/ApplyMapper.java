package com.jf.school.mapper;


import com.jf.school.bean.table.Apply;
import com.jf.school.bean.table.Order;
import tk.mybatis.mapper.common.Mapper;


public interface ApplyMapper extends Mapper<Apply> {


    Integer getLastApplyNumber();

    Integer selectTotal();
}

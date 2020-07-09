package com.jf.school.mapper;


import com.jf.school.bean.table.Money;
import com.jf.school.bean.table.Order;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;


public interface MoneyMapper extends Mapper<Money> {


    Money query();


    int update(@Param("ourMoney")Double ourMoney,@Param("otherMoney") Double otherMoney);
}

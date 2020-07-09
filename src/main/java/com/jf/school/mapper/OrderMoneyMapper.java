package com.jf.school.mapper;


import com.jf.school.bean.table.OrderMoney;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface OrderMoneyMapper extends Mapper<OrderMoney> {


    OrderMoney selectByFkOrder(@Param("fkOrder") Integer fkOrder);


}

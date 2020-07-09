package com.jf.school.mapper;


import com.jf.school.bean.table.InHouse;
import com.jf.school.bean.vo.MoneyInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface InHouseMapper extends Mapper<InHouse> {


    List<MoneyInfo> selectGroup();

    List<InHouse> getMoneyInfoByMonth(Long first, Long last);
}

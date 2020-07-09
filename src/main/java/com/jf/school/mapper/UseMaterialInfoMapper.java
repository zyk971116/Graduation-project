package com.jf.school.mapper;


import com.jf.school.bean.table.InHouse;
import com.jf.school.bean.table.UseMaterialInfo;
import com.jf.school.bean.vo.UseMaterialVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UseMaterialInfoMapper extends Mapper<UseMaterialInfo> {


    Float getOneInfo();

    Float getTwoInfo();

    Float getThreeInfo();

    List<UseMaterialVO> selectGroup();

    List<Integer> getNumbersByMonth(Long first, Long last);

    Integer selectByNumber(Long first, Long last, Integer number);

    UseMaterialInfo selectMaterial(Integer integer);


    String selectGroupUse();

    Float getTotalByMonth(Long first, Long last, int i);

}

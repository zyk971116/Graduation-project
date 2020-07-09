package com.jf.school.mapper;

import com.jf.school.bean.table.OtherMaterial;
import com.jf.school.bean.table.OurMaterial;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


public interface OtherMaterialMapper extends Mapper<OtherMaterial> {


    List<OtherMaterial> selectByCategory(@Param("category") String category);

    Map selectPriceAndTotal(@Param("category") String category, @Param("name") String name, @Param("quality") String quality);

    Integer getLastNumber();
}

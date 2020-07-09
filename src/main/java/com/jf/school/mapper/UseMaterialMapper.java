package com.jf.school.mapper;

import com.jf.school.bean.table.OurMaterial;
import com.jf.school.bean.table.UseMaterial;
import com.jf.school.bean.vo.MaterialQualityVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UseMaterialMapper extends Mapper<UseMaterial> {

    Integer getLastNumber();

    List<OurMaterial> selectByCategory(@Param("category") String category);

    List<MaterialQualityVO> selectQuality(@Param("category") String category, @Param("name") String name);

    Integer selectTotal(@Param("category") String category, @Param("name") String name, @Param("quality") String quality);
}

package com.jf.school.mapper;


import com.jf.school.bean.table.Apply;
import com.jf.school.bean.table.Archive;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface ArchiveMapper extends Mapper<Archive> {


    List<Archive> selectByState();

}

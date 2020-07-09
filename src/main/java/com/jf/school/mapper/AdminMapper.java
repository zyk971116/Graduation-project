package com.jf.school.mapper;


import com.jf.school.bean.table.Admin;
import tk.mybatis.mapper.common.Mapper;


public interface AdminMapper extends Mapper<Admin> {


    Integer getLastNumber();

    Admin selectById(String  id);
}

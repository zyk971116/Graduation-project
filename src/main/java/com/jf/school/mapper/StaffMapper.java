package com.jf.school.mapper;


import com.jf.school.bean.table.Admin;
import com.jf.school.bean.table.Staff;
import tk.mybatis.mapper.common.Mapper;


public interface StaffMapper extends Mapper<Staff> {


    Integer getLastNumber();

    Staff selectById(String  id);
}

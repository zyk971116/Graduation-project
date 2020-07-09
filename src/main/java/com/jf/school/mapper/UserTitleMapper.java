package com.jf.school.mapper;


import com.jf.school.bean.table.Admin;
import com.jf.school.bean.table.UserTitle;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserTitleMapper extends Mapper<UserTitle> {


    List<String> getRolesByUserId(String id);
}

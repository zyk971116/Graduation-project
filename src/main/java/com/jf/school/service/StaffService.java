package com.jf.school.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.school.bean.PageBean;
import com.jf.school.bean.Result;
import com.jf.school.bean.table.Admin;
import com.jf.school.bean.table.Staff;
import com.jf.school.bean.table.UserTitle;
import com.jf.school.mapper.AdminMapper;
import com.jf.school.mapper.StaffMapper;
import com.jf.school.mapper.UserTitleMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class StaffService {

    @Resource
    StaffMapper staffMapper;

    @Value("${default.password}")
    String password;

    @Resource
    UserTitleMapper userTitleMapper;

    public Result list(Integer currentPage,Integer pageSize,String name) {
        PageHelper.startPage(currentPage, pageSize);

        Example example=new Example(Staff.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");

        List<Staff> staffs = staffMapper.selectByExample(example);
        // 获取分页信息
        PageInfo<Staff> pageInfo = new PageInfo<>(staffs);

        PageBean pageBean = new PageBean();
        pageBean.setRows(staffs);
        pageBean.setTotal(pageInfo.getTotal());
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);

    }

    public Result addStaff(Staff staff) {
        staff.setId(UUID.randomUUID().toString());
        Integer number=staffMapper.getLastNumber()+1;
        staff.setNumber(String.valueOf(number));
        // 设置默认密码
        staff.setPassword(DigestUtils.md5DigestAsHex((number.toString() + password).getBytes()));
        staffMapper.insertSelective(staff);

        // 添加后，设置默认角色
        UserTitle ut = new UserTitle();
        ut.setFkTitle(2);
        ut.setFkUserId(staff.getId());
        userTitleMapper.insert(ut);
        return new Result(Result.CODE_SUCCESS, "添加成功");
    }

    public Result get(String id) {
        Staff staff = staffMapper.selectById(id);
        return new Result(Result.CODE_SUCCESS, "查询成功",staff);
    }

    public Result updateById(Staff staff) {
        staff.setPassword(DigestUtils.md5DigestAsHex((staff.getNumber() + staff.getPassword()).getBytes()));
        int count = staffMapper.updateByPrimaryKeySelective(staff);
        return new Result(Result.CODE_SUCCESS, "更新成功");
    }
}

package com.jf.school.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.school.bean.PageBean;
import com.jf.school.bean.Result;
import com.jf.school.bean.table.Admin;
import com.jf.school.bean.table.Business;
import com.jf.school.bean.table.OurMaterial;
import com.jf.school.bean.table.UserTitle;
import com.jf.school.mapper.AdminMapper;
import com.jf.school.mapper.UserTitleMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService {

    @Resource
    AdminMapper adminMapper;

    @Value("${default.password}")
    String password;

    @Resource
    UserTitleMapper userTitleMapper;

    public Result list(Integer currentPage,Integer pageSize,String name) {
        PageHelper.startPage(currentPage, pageSize);

        Example example=new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");

        List<Admin> admins = adminMapper.selectByExample(example);
        // 获取分页信息
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);

        PageBean pageBean = new PageBean();
        pageBean.setRows(admins);
        pageBean.setTotal(pageInfo.getTotal());
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);

    }

    public Result addAdmin(Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        Integer number=adminMapper.getLastNumber()+1;
        admin.setNumber(String.valueOf(number));
        // 设置默认密码
        admin.setPassword(DigestUtils.md5DigestAsHex((number.toString() + password).getBytes()));
        adminMapper.insertSelective(admin);

        // 添加后，设置默认角色
        UserTitle ut = new UserTitle();
        ut.setFkTitle(1);
        ut.setFkUserId(admin.getId());
        userTitleMapper.insert(ut);
        return new Result(Result.CODE_SUCCESS, "添加成功");
    }

    public Result get(String id) {
        Admin admin = adminMapper.selectById(id);
        return new Result(Result.CODE_SUCCESS, "查询成功",admin);
    }

    public Result updateById(Admin admin) {
        admin.setPassword(DigestUtils.md5DigestAsHex((admin.getNumber() + admin.getPassword()).getBytes()));
        int count = adminMapper.updateByPrimaryKeySelective(admin);
        return new Result(Result.CODE_SUCCESS, "更新成功");
    }
}

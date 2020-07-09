package com.jf.school.service;


import com.jf.school.bean.Result;
import com.jf.school.bean.table.Admin;
import com.jf.school.bean.table.Manager;
import com.jf.school.bean.table.Staff;
import com.jf.school.mapper.*;
import com.jf.school.utils.Constant;
import com.jf.school.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {

    @Resource
    JwtUtil jwtUtil;

    @Resource
    AdminMapper adminMapper;

    @Resource
    UserTitleMapper userTitleMapper;

    @Resource
    StaffMapper staffMapper;

    @Resource
    ManagerMapper managerMapper;

    public Result adminLogin(String number, String password) {
        // 查询用户信息
        Admin adminQuery = new Admin();
        adminQuery.setNumber(number);
        // 对密码进行加验，加密
        String newPassword = DigestUtils.md5DigestAsHex((number + password).getBytes());
        adminQuery.setPassword(newPassword);
        Admin admin = adminMapper.selectOne(adminQuery);


        if (admin == null) {
            return new Result(Result.CODE_FAILURE, "用户名或密码错误");
        }

        // 返回模拟的成功数据
        Map<String, String> map = new HashMap<>();
        //角色信息
        List<String> roles= userTitleMapper.getRolesByUserId(admin.getId());
        String token=jwtUtil.createJWT(admin.getId(),roles.toString(), Constant.UserType.ADMIN);

        map.put("token", token);
        return new Result(Result.CODE_SUCCESS, "登陆成功", map);
    }

    public Result staffLogin(String number, String password) {
        // 查询用户信息
        Staff staffQuery = new Staff();
        staffQuery.setNumber(number);
        // 对密码进行加验，加密
        String newPassword = DigestUtils.md5DigestAsHex((number + password).getBytes());
        staffQuery.setPassword(newPassword);
        Staff staff = staffMapper.selectOne(staffQuery);

        if (staff == null) {
            return new Result(Result.CODE_FAILURE, "用户名或密码错误");
        }

        // 返回模拟的成功数据
        Map<String, String> map = new HashMap<>();
        //角色信息

        List<String> roles= userTitleMapper.getRolesByUserId(staff.getId());

        String token=jwtUtil.createJWT(staff.getId(),roles.toString(), Constant.UserType.STAFF);

        map.put("token", token);
        return new Result(Result.CODE_SUCCESS, "登陆成功", map);

    }

    public Result managerLogin(String number, String password) {
        // 查询用户信息
        Manager managerQuery = new Manager();
        managerQuery.setNumber(number);
        // 对密码进行加验，加密
        String newPassword = DigestUtils.md5DigestAsHex((number + password).getBytes());
        managerQuery.setPassword(newPassword);
        Manager manager = managerMapper.selectOne(managerQuery);

        if (manager == null) {
            return new Result(Result.CODE_FAILURE, "用户名或密码错误");
        }

        // 返回模拟的成功数据
        Map<String, String> map = new HashMap<>();
        //角色信息

        List<String> roles= userTitleMapper.getRolesByUserId(manager.getId());

        String token=jwtUtil.createJWT(manager.getId(),roles.toString(), Constant.UserType.MANAGER);

        map.put("token", token);
        return new Result(Result.CODE_SUCCESS, "登陆成功", map);

    }

    public Result info(String token) {
        // 获取用户id
        String userId = jwtUtil.getUserId(token);

        // 根据用户id查询用户数据，需要知道当前的id是属于哪个表的id
        // 能否通过token来判断此id是哪个表的id？
        // 让token存储用户类型，我们直接从 token 中获取用户类型即可。
        Integer userType = jwtUtil.getUserType(token);

        Map<String, Object> map = new HashMap<>();

        if (userType == Constant.UserType.ADMIN) {
            Admin admin = adminMapper.selectByPrimaryKey(userId);
            map.put("number", admin.getNumber());
        }

        if (userType == Constant.UserType.STAFF) {
            Staff staff=staffMapper.selectByPrimaryKey(userId);
            map.put("number", staff.getNumber());
        }

        if (userType == Constant.UserType.MANAGER) {
            Manager manager=managerMapper.selectByPrimaryKey(userId);
            map.put("number", manager.getNumber());
        }


        // 查询用户角色信息
        List<String> roles = userTitleMapper.getRolesByUserId(userId);
        map.put("roles", roles);
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("introduction", "hello");

        return new Result(Result.CODE_SUCCESS, "查询成功", map);
    }




}

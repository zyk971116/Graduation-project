package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> map) {

        Integer role = Integer.parseInt(map.get("role"));
        switch (role) {
            case 1:
                // 管理员登陆
                return userService.adminLogin(map.get("username"), map.get("password"));
            case 2:
                // 员工登陆
                return userService.staffLogin(map.get("username"), map.get("password"));
            case 3:
                // 员工登陆
                return userService.managerLogin(map.get("username"), map.get("password"));

        }

        return new Result(Result.CODE_SUCCESS, "登陆成功");
    }

    @GetMapping("/info")
    public Result info(String token) {
        return userService.info(token);
    }

    @PostMapping("/logout")
    public String logout() {
        return "{\"code\":20000,\"data\":\"success\"}";
    }

}

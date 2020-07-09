package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.Admin;
import com.jf.school.bean.table.Business;
import com.jf.school.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result list(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return adminService.list(currentPage, pageSize,name);
    }

    @PostMapping("/addAdmin")
    public Result addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    @GetMapping("/getAdmin/{id}")
    public Result getAdminById(@PathVariable String id) {
        return adminService.get(id);
    }

    @PutMapping("/updateBusiness")
    public Result updateById(@RequestBody Admin admin) {
        return adminService.updateById(admin);
    }

}

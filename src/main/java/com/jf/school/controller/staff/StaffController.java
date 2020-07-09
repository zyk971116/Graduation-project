package com.jf.school.controller.staff;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.Admin;
import com.jf.school.bean.table.Business;
import com.jf.school.bean.table.Staff;
import com.jf.school.service.AdminService;
import com.jf.school.service.StaffService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Resource
    StaffService staffService;

    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result list(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return staffService.list(currentPage, pageSize,name);
    }

    @PostMapping("/addStaff")
    public Result addStaff(@RequestBody Staff staff) {
        return staffService.addStaff(staff);
    }

    @GetMapping("/getStaff/{id}")
    public Result getStaffById(@PathVariable String id) {
        return staffService.get(id);
    }

    @PutMapping("/updateBusiness")
    public Result updateById(@RequestBody Staff staff) {
        return staffService.updateById(staff);
    }

}

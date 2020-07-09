package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.Business;
import com.jf.school.service.BusinessService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/business")
public class BusinessController {

    @Resource
    BusinessService businessService;

    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result list(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return businessService.list(currentPage, pageSize,name);
    }

    @GetMapping("/list")
    public Result list() {
        return businessService.list();
    }

    @PostMapping("/addBusiness")
    public Result addBusiness(@RequestBody Business business) {
        return businessService.addBusiness(business);
    }

    @GetMapping("/getBusiness/{id}")
    public Result getBusinessById(@PathVariable Integer id) {
        return businessService.get(id);
    }

    @PutMapping("/updateBusiness")
    public Result updateById(@RequestBody Business business) {
        return businessService.updateById(business);
    }

    @GetMapping("/getDefectiveRate/{id}")
    public Result getDefectiveRate(@PathVariable Integer id) {
        return businessService.getDefectiveRate(id);
    }

}

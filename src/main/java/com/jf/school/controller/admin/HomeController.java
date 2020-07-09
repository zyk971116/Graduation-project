package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.service.AdminService;
import com.jf.school.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;

@RestController
@RequestMapping("/admin/home")
public class HomeController {

    @Resource
    HomeService homeService;

    @GetMapping("/getInfo")
    public Result getInfo() throws ParseException {
        return homeService.getInfo();
    }

}

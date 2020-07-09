package com.jf.school.controller.staff;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.OurMaterial;
import com.jf.school.service.OurMaterialService;
import com.jf.school.service.UseMaterialService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/staff/useMaterial")
public class UseMaterialController {

    @Resource
    UseMaterialService useMaterialService;

    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result list(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return useMaterialService.list(currentPage, pageSize,name);
    }

    @PutMapping("/use/{useId}/{useTotal}")
    public Result useMaterial( @PathVariable Integer useId,@PathVariable Integer useTotal) {
        return useMaterialService.useMaterial(useId,useTotal);
    }

    @GetMapping("/infoList/{currentPage}/{pageSize}")
    public Result infoList(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return useMaterialService.infoList(currentPage, pageSize,name);
    }


}

package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.OtherMaterial;
import com.jf.school.bean.table.OurMaterial;
import com.jf.school.service.OtherMaterialService;
import com.jf.school.service.OurMaterialService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/otherMaterial")
public class OtherMaterialController {

    @Resource
    OtherMaterialService otherMaterialService;

    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result list(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return otherMaterialService.list(currentPage, pageSize,name);
    }
    @PostMapping("/addMaterial")
    public Result addMaterial(@RequestBody OtherMaterial material) {
        return otherMaterialService.addMaterial(material);
    }


    @PutMapping("/updateMaterial")
    public Result updateById(@RequestBody OtherMaterial material) {
        return otherMaterialService.updateById(material);
    }


    @GetMapping("/getMaterial/{id}")
    public Result get(@PathVariable Integer id) {
        return otherMaterialService.get(id);
    }


    @GetMapping("/getMaterByCategory/{category}")
    public Result getMaterByCategory( @PathVariable String category) {
        return otherMaterialService.getMaterByCategory(category);
    }

    @GetMapping("/getPrice")
    public Result getPrice( @RequestParam(defaultValue = "",required = false) String category,
                            @RequestParam(defaultValue = "",required = false) String name,
                            @RequestParam(defaultValue = "",required = false) String quality) {
        return otherMaterialService.getPrice(category,name,quality);
    }
}

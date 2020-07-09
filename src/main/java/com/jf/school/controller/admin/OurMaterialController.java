package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.OurMaterial;
import com.jf.school.service.OurMaterialService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/ourMaterial")
public class OurMaterialController {

    @Resource
    OurMaterialService ourMaterialService;

    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result list(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return ourMaterialService.list(currentPage, pageSize,name);
    }

    @PostMapping("/addMaterial")
    public Result addMaterial(@RequestBody OurMaterial material) {
        return ourMaterialService.addMaterial(material);
    }

    @GetMapping("/getMaterial/{id}")
    public Result get(@PathVariable Integer id) {
        return ourMaterialService.get(id);
    }

    @PutMapping("/updateMaterial")
    public Result updateById(@RequestBody OurMaterial material) {
        return ourMaterialService.updateById(material);
    }

    @GetMapping("/getMaterByCategory/{category}")
    public Result getMaterByCategory( @PathVariable String category) {
        return ourMaterialService.getMaterByCategory(category);
    }

    @GetMapping("/getQuality/{category}/{name}")
    public Result getQuality( @PathVariable String category,@PathVariable String name) {
        return ourMaterialService.getQuality(category,name);
    }

    @GetMapping("/getStock/{category}/{name}/{quality}")
    public Result getStock( @PathVariable String category,@PathVariable String name,@PathVariable String quality) {
        return ourMaterialService.getStock(category,name,quality);
    }


}

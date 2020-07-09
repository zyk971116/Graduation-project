package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.OurTruck;
import com.jf.school.bean.table.Truck;
import com.jf.school.service.OurTruckService;
import com.jf.school.service.TruckService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/ourTruck")
public class OurTruckController {

    @Resource
    OurTruckService ourTruckService;

    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result list(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return ourTruckService.list(currentPage, pageSize,name);
    }

    @PostMapping("/addTruck")
    public Result addTruck(@RequestBody OurTruck ourTruck) {
        return ourTruckService.addTruck(ourTruck);
    }

    @GetMapping("/getTruck/{id}")
    public Result getTruckById(@PathVariable Integer id) {
        return ourTruckService.get(id);
    }

    @PutMapping("/updateTruck")
    public Result updateById(@RequestBody OurTruck ourTruck) {
        return ourTruckService.updateById(ourTruck);
    }

    @GetMapping("/list")
    public Result getTruckList( ){
        return ourTruckService.getTruckList();
    }

    @GetMapping("/getTruckByApplyId/{ApplyId}")
    public Result getTruckByApplyId(@PathVariable Integer ApplyId){
        return ourTruckService.getTruckByApplyId(ApplyId);
    }

}

package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.Business;
import com.jf.school.bean.table.Truck;
import com.jf.school.service.BusinessService;
import com.jf.school.service.TruckService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/truck")
public class TruckController {

    @Resource
    TruckService truckService;

    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result list(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return truckService.list(currentPage, pageSize,name);
    }

    @PostMapping("/addTruck")
    public Result addTruck(@RequestBody Truck truck) {
        return truckService.addTruck(truck);
    }

    @GetMapping("/getTruck/{id}")
    public Result getTruckById(@PathVariable Integer id) {
        return truckService.get(id);
    }

    @PutMapping("/updateTruck")
    public Result updateById(@RequestBody Truck truck) {
        return truckService.updateById(truck);
    }

    @GetMapping("/list")
    public Result getTruckList( ){
        return truckService.getTruckList();
    }

    @GetMapping("/getTruckByOrderId/{orderId}")
    public Result getTruckByOrderId(@PathVariable Integer orderId){
        return truckService.getTruckByOrderId(orderId);
    }

}

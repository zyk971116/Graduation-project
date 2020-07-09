package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.OrderTruck;
import com.jf.school.service.OrderTruckService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/orderTruck")
public class OrderTruckController {

    @Resource
    OrderTruckService orderTruckService;

    @PostMapping("/addTruckToOrder/{orderId}/{truckId}")
    public Result addTruckToOrder(@PathVariable Integer orderId,
                                  @PathVariable Integer truckId) {
        return orderTruckService.addTruckToOrder(orderId,truckId);
    }

}

package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.Business;
import com.jf.school.bean.table.Order;
import com.jf.school.mapper.OrderMapper;
import com.jf.school.service.BusinessService;
import com.jf.school.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/admin/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result list(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return orderService.list(currentPage, pageSize,name);
    }

    @GetMapping("/listByState/{currentPage}/{pageSize}")
    public Result listByState(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return orderService.listByState(currentPage, pageSize,name);
    }

    @GetMapping("/getListByState/{currentPage}/{pageSize}")
    public Result getListByState(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return orderService.getListByState(currentPage, pageSize,name);
    }

    @GetMapping("/getOrderNumber")
    public Result getOrderNumber() {
        return orderService.getOrderNumber();
    }

    @PostMapping("/addApplyOrder")
    public Result addApplyOrder(@RequestBody Order order,@RequestHeader("XToken") String  token) {
        return orderService.addApplyOrder(order,token);
    }

    @PutMapping("/updateOrderState/{id}/{state}")
    public Result updateOrderState(@PathVariable Integer id, @PathVariable Integer state,@RequestHeader("XToken") String  token) throws Exception {
        return orderService.updateOrderState(id,state,token);
    }


    @PutMapping("/updateStateById/{id}/{state1}")
    public Result updateStateById(@PathVariable Integer id, @PathVariable Integer state1) {
        return orderService.updateStateById(id,state1);
    }

    @PutMapping("/trueOrder/{id}")
    public Result trueOrder(@PathVariable Integer id) {
        return orderService.trueOrder(id);
    }

    @PutMapping("/trueSomeOrder")
    public Result trueSomeOrder(@RequestBody Map<String, Integer> map) {
        return orderService.trueSomeOrder(map);
    }


    @GetMapping("/getListByOrderId/{id}")
    public Result getListByOrderId(@PathVariable Integer id) {
        return orderService.getListByOrderId(id);
    }

}

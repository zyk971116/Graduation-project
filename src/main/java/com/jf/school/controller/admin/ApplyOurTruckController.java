package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.mapper.ApplyOurTruckMapper;
import com.jf.school.service.ApplyOurTruckService;
import com.jf.school.service.OrderTruckService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/ApplyTruck")
public class ApplyOurTruckController {

    @Resource
    ApplyOurTruckService applyOurTruckService;

    @PostMapping("/addTruckToApply/{applyId}/{truckId}")
    public Result addTruckToApply(@PathVariable Integer applyId,
                                  @PathVariable Integer truckId) {
        return applyOurTruckService.addTruckToApply(applyId,truckId);
    }

}

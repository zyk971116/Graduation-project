package com.jf.school.controller.staff;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.Apply;
import com.jf.school.bean.table.Order;
import com.jf.school.service.ApplyService;
import com.jf.school.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/staff/apply")
public class ApplyController {


    @Resource
    ApplyService applyService;

    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result list(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return applyService.list(currentPage, pageSize,name);
    }

    @GetMapping("/getListByState/{currentPage}/{pageSize}")
    public Result getListByState(@PathVariable Integer currentPage,
                       @PathVariable Integer pageSize,
                       @RequestParam(defaultValue = "",required = false) String name) {
        return applyService.getListByState(currentPage, pageSize,name);
    }

    @GetMapping("/getApplyList/{currentPage}/{pageSize}")
    public Result getApplyList(@PathVariable Integer currentPage,
                                 @PathVariable Integer pageSize,
                                 @RequestParam(defaultValue = "",required = false) String name) {
        return applyService.getApplyList(currentPage, pageSize,name);
    }

    @GetMapping("/getApplyNumber")
    public Result getApplyNumber() {
        return applyService.getApplyNumber();
    }


    @PostMapping("/addApply")
    public Result addApply(@RequestBody Apply apply, @RequestHeader("XToken") String  token) {
        return applyService.addApply(apply,token);
    }


    @PutMapping("/updateStateById/{id}/{state}")
    public Result updateStateById(@PathVariable Integer id, @PathVariable Integer state,@RequestHeader("XToken") String  token) {
        return applyService.updateStateById(id,state,token);
    }

    @PutMapping("/trueApply/{id}")
    public Result trueApply(@PathVariable Integer id) {
        return applyService.trueApply(id);
    }



}

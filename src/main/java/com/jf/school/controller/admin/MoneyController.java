package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.Order;
import com.jf.school.service.MoneyService;
import com.jf.school.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/admin/money")
public class MoneyController {

    @Resource
    MoneyService moneyService;

    @GetMapping("/list")
    public Result list() throws ParseException {
        return moneyService.list();
    }

    @GetMapping()
    public Result getMoney()  {
        return moneyService.getMoney();
    }

    @PutMapping("/addMoney/{add}")
    public Result addMoney(@PathVariable Double add)  {
        return moneyService.addMoney(add);
    }

}

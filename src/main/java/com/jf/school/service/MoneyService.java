package com.jf.school.service;

import com.github.pagehelper.PageHelper;
import com.jf.school.bean.Result;
import com.jf.school.bean.table.*;
import com.jf.school.bean.vo.MoneyInfo;
import com.jf.school.mapper.*;
import com.jf.school.utils.AnswerUtils;
import com.jf.school.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MoneyService {

    @Resource
    MoneyMapper moneyMapper;


    @Resource
    InHouseMapper inHouseMapper;

    @Resource
    AdminMapper adminMapper;

    @Resource
    BusinessMapper businessMapper;


    public Result list() throws ParseException {

        List<MoneyInfo> moneyInfos = inHouseMapper.selectGroup();
        for (MoneyInfo moneyInfo : moneyInfos) {
            Long first= DateUtils.getFirstDayOfMoth(moneyInfo.getDate());
            Long last= DateUtils.getLastDayOfMoth(moneyInfo.getDate());
            //根据开始和结束时间戳查询列表
            List<InHouse> inHouses=inHouseMapper.getMoneyInfoByMonth(first, last);
            Double count= 0.0;

            for (InHouse inHouse : inHouses) {
                inHouse.setCategory(AnswerUtils.getDisplayAnswer(inHouse.getCategory()));
                inHouse.setQuality(AnswerUtils.getDisplayAnswer(inHouse.getQuality()));
                Admin admin = adminMapper.selectByPrimaryKey(inHouse.getFkUser());
                inHouse.setFkUser(admin.getName());
                Business business = businessMapper.selectByPrimaryKey(inHouse.getFkCompany());
                inHouse.setFkCompany(business.getName());
                count=count+inHouse.getInPrice();
            }
            moneyInfo.setInHouses(inHouses);
            moneyInfo.setCount(count);

        }

        return new Result(Result.CODE_SUCCESS, "查询成功",moneyInfos);
    }

    public Result getMoney() {
        List<InHouse> inHouses = inHouseMapper.selectAll();
        Double out= 0.0;
        for (InHouse inHouse : inHouses) {
            if (inHouse.getInTotal()==null){
                out=out+inHouse.getTotalPrice();
            }
        }
        Money money = moneyMapper.selectOne(null);
        Double ourMoney= money.getOurMoney();
        Map<String ,Double> map=new HashMap<>();
        map.put("ourMoney",ourMoney);
        map.put("out",out);
        return new Result(Result.CODE_SUCCESS, "查询成功",map);
    }

    public Result addMoney(Double add) {
        Money money = moneyMapper.selectOne(null);
        moneyMapper.update(money.getOurMoney()+add,money.getOtherMoney());
        return new Result(Result.CODE_SUCCESS, "加钱成功");
    }
}

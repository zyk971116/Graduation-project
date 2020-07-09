package com.jf.school.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.school.bean.PageBean;
import com.jf.school.bean.Result;
import com.jf.school.bean.table.*;
import com.jf.school.bean.vo.InHouseVO;
import com.jf.school.bean.vo.OrderVO;
import com.jf.school.mapper.*;
import com.jf.school.utils.DateUtils;
import com.jf.school.utils.JwtUtil;
import com.jf.school.utils.MailUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

@Service
public class OrderService {

    @Resource
    OrderMapper orderMapper;

    @Resource
    BusinessMapper businessMapper;

    @Resource
    AdminMapper adminMapper;

    @Resource
    TruckMapper truckMapper;

    @Resource
    OrderTruckMapper orderTruckMapper;

    @Resource
    InHouseMapper inHouseMapper;

    @Resource
    OurMaterialMapper ourMaterialMapper;

    @Resource
    OtherMaterialMapper otherMaterialMapper;

    @Resource
    MoneyMapper moneyMapper;

    @Resource
    OrderMoneyMapper orderMoneyMapper;

    @Resource
    JwtUtil jwtUtil;

    @Resource
    ManagerMapper managerMapper;

    //获取订单列表
    public Result list(Integer currentPage, Integer pageSize, String name) {
        PageHelper.startPage(currentPage, pageSize);
        //添加模糊查询条件
        Example example=new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");
        criteria.andBetween("state","4","6");

        List<Order> orders = orderMapper.selectByExample(example);
        // 获取分页信息
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        // Order 转成 OrderVO
        List<OrderVO> vos = new ArrayList<>();
        for (Order order : orders) {
            OrderVO vo=new OrderVO();
            vo.setId(order.getId());
            vo.setOrderNumber(order.getOrderNumber());
            vo.setName(order.getName());
            vo.setCategory(order.getCategory());
            vo.setQuality(order.getQuality());
            vo.setPrice(order.getPrice());
            vo.setTotal(order.getTotal());
            vo.setTotalPrice(order.getTotalPrice());
            vo.setCreated(order.getCreated());
            vo.setState(order.getState());

            Business business = businessMapper.selectByPrimaryKey(order.getFkCompany());
            vo.setCompany(business);
            Admin user = adminMapper.selectByPrimaryKey(order.getFkUser());
            vo.setUser(user);
            vos.add(vo);
        }

        PageBean pageBean = new PageBean(pageInfo.getTotal(), vos);
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);
    }

    public Result listByState(Integer currentPage, Integer pageSize, String name) {
        PageHelper.startPage(currentPage, pageSize);
        //添加模糊查询条件
        Example example=new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");
        criteria.andEqualTo("state","5");


        List<Order> orders = orderMapper.selectByExample(example);
        // 获取分页信息
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        // Order 转成 OrderVO
        List<OrderVO> vos = new ArrayList<>();
        for (Order order : orders) {
            OrderVO vo=new OrderVO();
            vo.setId(order.getId());
            vo.setOrderNumber(order.getOrderNumber());
            vo.setName(order.getName());
            vo.setCategory(order.getCategory());
            vo.setQuality(order.getQuality());
            vo.setPrice(order.getPrice());
            vo.setTotal(order.getTotal());
            vo.setTotalPrice(order.getTotalPrice());
            vo.setCreated(order.getCreated());
            vo.setState(order.getState());
            vo.setState1(order.getState1());

            Business business = businessMapper.selectByPrimaryKey(order.getFkCompany());
            vo.setCompany(business);
            Admin user = adminMapper.selectByPrimaryKey(order.getFkUser());
            vo.setUser(user);
            vos.add(vo);
        }

        PageBean pageBean = new PageBean(pageInfo.getTotal(), vos);
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);
    }

    //获取最新订单编号
    public Result getOrderNumber() {
        Integer orderNumber= orderMapper.getLastOrderNumber() +1;
        return new Result(Result.CODE_SUCCESS, "获取成功", orderNumber);
    }

    //管理提交申请订单
    @Transactional
    public Result addApplyOrder(Order order,String token) {
        String userId = jwtUtil.getUserId(token);
        order.setCreated(DateUtils.getUnixTimeStamp());
        order.setState(4);
        order.setFkUser(Integer.valueOf(userId));
        int count = orderMapper.insertSelective(order);

        return new Result(Result.CODE_SUCCESS, "添加成功");
    }

    //经理同意申请,提交订单
    @Transactional
    public Result updateOrderState(Integer id, Integer state,String token) throws Exception {
        String userId = jwtUtil.getUserId(token);
//        order.setCreated(DateUtils.getUnixTimeStamp());
//        order.setState(4);
//        order.setFkUser(Integer.valueOf(userId));
//        int count = orderMapper.insertSelective(order);
        Order order = orderMapper.selectByPrimaryKey(id);
        if(state==5){
            Manager manager = managerMapper.selectByPrimaryKey(userId);
            MailUtils.sendMail(order,manager.getName(),manager.getTel());
        }
        order.setState(state);
        order.setState1(0);
        int count = orderMapper.updateByPrimaryKeySelective(order);

       /* //扣除订单的金额
        Money money= moneyMapper.query();
        int i= moneyMapper.update((money.getOurMoney() - order.getTotalPrice()),money.getOtherMoney());
        //将订单的金额存入第三方保管
        OrderMoney orderMoney=new OrderMoney();
        orderMoney.setFkOrder(order.getId());
        orderMoney.setMidMoney(order.getTotalPrice());

        int insert = orderMoneyMapper.insert(orderMoney);*/


        return new Result(Result.CODE_SUCCESS, "添加成功");
    }

    //接受订单并将订单添加到入库信息里 或者 拒绝订单
    @Transactional
    public Result updateStateById(Integer id, Integer state1) {
        Order order = orderMapper.selectByPrimaryKey(id);
        order.setState1(state1);
        orderMapper.updateByPrimaryKey(order);
        if (state1==1){
            //扣除订单的金额
            Money money= moneyMapper.query();
            int i= moneyMapper.update((money.getOurMoney() - order.getTotalPrice()),money.getOtherMoney());
            //将订单的金额存入第三方保管
            OrderMoney orderMoney=new OrderMoney();
            orderMoney.setFkOrder(order.getId());
            orderMoney.setMidMoney(order.getTotalPrice());

            int insert = orderMoneyMapper.insert(orderMoney);

            InHouse inHouse=new InHouse();
            inHouse.setId(order.getId());
            inHouse.setOrderNumber(order.getOrderNumber());
            inHouse.setName(order.getName());
            inHouse.setCategory(order.getCategory());
            inHouse.setQuality(order.getQuality());
            inHouse.setPrice(order.getPrice());
            inHouse.setTotal(order.getTotal());
            inHouse.setTotalPrice(order.getTotalPrice());
            inHouse.setFkCompany(String.valueOf(order.getFkCompany()));
            inHouse.setFkUser(String.valueOf(order.getFkUser()));
            inHouse.setCreated(order.getCreated());
            inHouse.setState(order.getState());
            inHouse.setState1(order.getState1());
            inHouse.setInPrice(0.00);
            inHouseMapper.insertSelective(inHouse);
        };

        //拒绝订单后,将买方的钱由第三方退回
//        if (state==2){
//            OrderMoney orderMoney = orderMoneyMapper.selectByFkOrder(id);
//            Double midMoney = orderMoney.getMidMoney();
//            Money money= moneyMapper.query();
//            moneyMapper.update((money.getOurMoney() + midMoney), money.getOtherMoney());
//
//        }
        return new Result(Result.CODE_SUCCESS, "操作成功");
    }

    //获取入库信息订单列表
    public Result getListByState(Integer currentPage, Integer pageSize, String name) {
        PageHelper.startPage(currentPage, pageSize);
        //添加模糊查询条件
        Example example=new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");
        criteria.andEqualTo("state","5");
        criteria.andNotEqualTo("state1","2");

        List<InHouse> inHouses = inHouseMapper.selectByExample(example);
        // 获取分页信息
        PageInfo<InHouse> pageInfo = new PageInfo<>(inHouses);

        List<InHouseVO> vos = new ArrayList<>();

        for (InHouse inHouse : inHouses) {
            InHouseVO vo=new InHouseVO();
            vo.setId(inHouse.getId());
            vo.setOrderNumber(inHouse.getOrderNumber());
            vo.setName(inHouse.getName());
            vo.setCategory(inHouse.getCategory());
            vo.setQuality(inHouse.getQuality());
            vo.setPrice(inHouse.getPrice());
            vo.setTotal(inHouse.getTotal());
            vo.setTotalPrice(inHouse.getTotalPrice());
            vo.setCreated(inHouse.getCreated());
            vo.setState(inHouse.getState());
            vo.setState1(inHouse.getState1());
            vo.setInTotal(inHouse.getInTotal());
            vo.setInPrice(inHouse.getInPrice());

            Business business = businessMapper.selectByPrimaryKey(inHouse.getFkCompany());
            vo.setCompany(business);
            Admin user = adminMapper.selectByPrimaryKey(inHouse.getFkUser());
            vo.setUser(user);
            vos.add(vo);
        }

        PageBean pageBean = new PageBean(pageInfo.getTotal(), vos);
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);

    }

    //确认入库订单收货成功
    @Transactional
    public Result trueOrder(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        order.setState1(3);
        orderMapper.updateByPrimaryKey(order);

        InHouse inHouse = inHouseMapper.selectByPrimaryKey(id);
        inHouse.setInTotal(order.getTotal());
        inHouse.setInPrice(order.getTotalPrice());
        inHouse.setState1(3);
        inHouseMapper.updateByPrimaryKey(inHouse);

        Example example1=new Example(OtherMaterial.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("name",inHouse.getName());
        criteria1.andEqualTo("category",inHouse.getCategory());
        criteria1.andEqualTo("price",inHouse.getPrice());
        criteria1.andEqualTo("quality",inHouse.getQuality());
        OtherMaterial otherMaterial = otherMaterialMapper.selectOneByExample(example1);

        Example example=new Example(OurMaterial.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",inHouse.getName());
        criteria.andEqualTo("category",inHouse.getCategory());
        criteria.andEqualTo("purchasePrice",inHouse.getPrice());
        criteria.andEqualTo("quality",inHouse.getQuality());
        OurMaterial ourMaterial = ourMaterialMapper.selectOneByExample(example);

        if (ourMaterial !=null ){
            ourMaterial.setTotal(ourMaterial.getTotal() + inHouse.getTotal());
            ourMaterialMapper.updateByPrimaryKeySelective(ourMaterial);
            otherMaterial.setTotal(otherMaterial.getTotal()-inHouse.getTotal());
            otherMaterialMapper.updateByPrimaryKeySelective(otherMaterial);
        }
        if (ourMaterial==null){
            OurMaterial newOurMaterial=new OurMaterial();
            Integer materialNumber = ourMaterialMapper.getLastNumber() + 1;
            newOurMaterial.setMaterialNumber(materialNumber);
            newOurMaterial.setName(inHouse.getName());
            newOurMaterial.setCategory(inHouse.getCategory());
            newOurMaterial.setPurchasePrice(inHouse.getPrice());
            newOurMaterial.setQuality(inHouse.getQuality());
            newOurMaterial.setTotal(inHouse.getTotal());
            ourMaterialMapper.insertSelective(newOurMaterial);
            otherMaterial.setTotal(otherMaterial.getTotal()-inHouse.getTotal());
            otherMaterialMapper.updateByPrimaryKeySelective(otherMaterial);
        }

        OrderMoney orderMoney = orderMoneyMapper.selectByFkOrder(id);
        Double midMoney = orderMoney.getMidMoney();
        Money money= moneyMapper.query();
        moneyMapper.update(money.getOurMoney(), (money.getOtherMoney()+ midMoney));

        List<Integer> truckIds= orderTruckMapper.selectByOrderId(id);
        for (Integer truckId : truckIds) {
            Truck truck =new Truck();
            truck.setId(truckId);
            truck.setState(0);
            truckMapper.updateByPrimaryKeySelective(truck);
        }

        orderTruckMapper.deleteByOrderId(id);

        return new Result(Result.CODE_SUCCESS, "操作成功");
    }

    //确认部分物资退货，其余收货成功
    @Transactional
    public Result trueSomeOrder(Map<String, Integer> map) {
        Integer id = map.get("id");
        Integer total =Integer.parseInt (String.valueOf(map.get("total")));
        Integer totalPrice = map.get("totalPrice");

        Order order = orderMapper.selectByPrimaryKey(id);
        order.setState1(3);
        orderMapper.updateByPrimaryKey(order);

        InHouse inHouse = inHouseMapper.selectByPrimaryKey(id);
        inHouse.setState1(3);
        inHouse.setInTotal(order.getTotal()-total);
        inHouse.setInPrice(order.getTotalPrice() - Double.valueOf(totalPrice));
        inHouseMapper.updateByPrimaryKey(inHouse);

        Example example1=new Example(OtherMaterial.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("name",inHouse.getName());
        criteria1.andEqualTo("category",inHouse.getCategory());
        criteria1.andEqualTo("price",inHouse.getPrice());
        criteria1.andEqualTo("quality",inHouse.getQuality());
        OtherMaterial otherMaterial = otherMaterialMapper.selectOneByExample(example1);

        Example example=new Example(OurMaterial.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",inHouse.getName());
        criteria.andEqualTo("category",inHouse.getCategory());
        criteria.andEqualTo("purchasePrice",inHouse.getPrice());
        criteria.andEqualTo("quality",inHouse.getQuality());
        OurMaterial ourMaterial = ourMaterialMapper.selectOneByExample(example);

        if (ourMaterial !=null ){
            ourMaterial.setTotal(ourMaterial.getTotal() + inHouse.getTotal() -  total);
            ourMaterialMapper.updateByPrimaryKeySelective(ourMaterial);
            otherMaterial.setTotal(otherMaterial.getTotal()-inHouse.getTotal()+ total);
            otherMaterialMapper.updateByPrimaryKeySelective(otherMaterial);
        }
        if (ourMaterial==null){
            OurMaterial newOurMaterial=new OurMaterial();
            Integer materialNumber = ourMaterialMapper.getLastNumber() + 1;
            newOurMaterial.setMaterialNumber(materialNumber);
            newOurMaterial.setName(inHouse.getName());
            newOurMaterial.setCategory(inHouse.getCategory());
            newOurMaterial.setPurchasePrice(inHouse.getPrice());
            newOurMaterial.setQuality(inHouse.getQuality());
            newOurMaterial.setTotal(inHouse.getTotal() - total);
            ourMaterialMapper.insertSelective(newOurMaterial);
            otherMaterial.setTotal(otherMaterial.getTotal()-inHouse.getTotal() + total);
            otherMaterialMapper.updateByPrimaryKeySelective(otherMaterial);
        }

        OrderMoney orderMoney = orderMoneyMapper.selectByFkOrder( id);
        Double midMoney = orderMoney.getMidMoney();
        orderMoney.setFalseMoney(Double.valueOf(totalPrice));
        orderMoneyMapper.updateByPrimaryKeySelective(orderMoney);
        Money money= moneyMapper.query();
        moneyMapper.update(money.getOurMoney()+ Double.valueOf(totalPrice), (money.getOtherMoney()+ midMoney - Double.valueOf(totalPrice)));


        List<Integer> truckIds= orderTruckMapper.selectByOrderId(id);
        for (Integer truckId : truckIds) {
            Truck truck =new Truck();
            truck.setId(truckId);
            truck.setState(0);
            truckMapper.updateByPrimaryKeySelective(truck);
        }

        orderTruckMapper.deleteByOrderId(id);

        return new Result(Result.CODE_SUCCESS, "操作成功");


    }

    //根据订单ID获取物资价格
    public Result getListByOrderId(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        Double price = order.getPrice();
        return new Result(Result.CODE_SUCCESS, "操作成功",price);
    }


}



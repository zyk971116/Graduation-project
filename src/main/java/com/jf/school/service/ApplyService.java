package com.jf.school.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.school.bean.PageBean;
import com.jf.school.bean.Result;
import com.jf.school.bean.table.*;
import com.jf.school.bean.vo.ApplyVO;
import com.jf.school.bean.vo.InHouseVO;
import com.jf.school.bean.vo.OrderVO;
import com.jf.school.bean.vo.OutHouseVO;
import com.jf.school.mapper.*;
import com.jf.school.utils.AnswerUtils;
import com.jf.school.utils.DateUtils;
import com.jf.school.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ApplyService {

    @Resource
    AnswerUtils answerUtils;

    @Resource
    ApplyMapper applyMapper;

    @Resource
    JwtUtil jwtUtil;

    @Resource
    StaffMapper staffMapper;

    @Resource
    AdminMapper adminMapper;


    @Resource
    OutHouseMapper outHouseMapper;

    @Resource
    OurMaterialMapper ourMaterialMapper;

    @Resource
    UseMaterialMapper useMaterialMapper;

    @Resource
    ApplyOurTruckMapper applyOurTruckMapper;

    @Resource
    OurTruckMapper ourTruckMapper;

    //获取申请列表
    public Result list(Integer currentPage, Integer pageSize, String name) {
        PageHelper.startPage(currentPage, pageSize);
        //添加模糊查询条件
        Example example=new Example(Apply.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");

        List<Apply> applies = applyMapper.selectByExample(example);
        // 获取分页信息
        PageInfo<Apply> pageInfo = new PageInfo<>(applies);
        // Order 转成 OrderVO
        List<ApplyVO> vos = new ArrayList<>();
        for (Apply apply: applies) {
            ApplyVO vo=new ApplyVO();
            vo.setId(apply.getId());
            vo.setOrderNumber(apply.getApplyNumber());
            vo.setName(apply.getName());
            vo.setCategory(apply.getCategory());
            vo.setQuality(apply.getQuality());
            vo.setTotal(apply.getTotal());
            vo.setCreated(apply.getCreated());
            vo.setState(apply.getState());

            Staff user = staffMapper.selectByPrimaryKey(apply.getFkUser());
            vo.setUser(user);
            vos.add(vo);
        }

        PageBean pageBean = new PageBean(pageInfo.getTotal(), vos);
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);
    }


    //获取最新订单编号
    public Result getApplyNumber() {
        Integer applyNumber= applyMapper.getLastApplyNumber() +1;
        return new Result(Result.CODE_SUCCESS, "获取成功", applyNumber);
    }

    //提交申请
    @Transactional
    public Result addApply(Apply apply,String token) {
        String userId = jwtUtil.getUserId(token);
        apply.setCreated(DateUtils.getUnixTimeStamp());
        apply.setState(0);
        apply.setFkUser(Integer.valueOf(userId));
        int count = applyMapper.insertSelective(apply);
        OutHouse outHouse=new OutHouse();
        outHouse.setId(apply.getId());
        outHouse.setOrderNumber(apply.getApplyNumber());
        outHouse.setName(apply.getName());
        outHouse.setCategory(apply.getCategory());
        outHouse.setQuality(apply.getQuality());
        outHouse.setTotal(apply.getTotal());
        outHouse.setFkUser(apply.getFkUser());
        outHouse.setCreated(apply.getCreated());
        outHouse.setState(0);
        outHouse.setFkAdmin(null);
        outHouseMapper.insertSelective(outHouse);


        return new Result(Result.CODE_SUCCESS, "添加成功");
    }

    //接受申请并将申请添加到出库信息里 或者 拒绝申请
    @Transactional
    public Result updateStateById(Integer id, Integer state,String  token) {
        Apply apply = applyMapper.selectByPrimaryKey(id);
        apply.setState(state);
        applyMapper.updateByPrimaryKey(apply);
       /* if (state==1){
            OutHouse outHouse=new OutHouse();
            outHouse.setId(apply.getId());
            outHouse.setOrderNumber(apply.getApplyNumber());
            outHouse.setName(apply.getName());
            outHouse.setCategory(apply.getCategory());
            outHouse.setQuality(apply.getQuality());
            outHouse.setTotal(apply.getTotal());
            outHouse.setFkUser(apply.getFkUser());
            outHouse.setCreated(apply.getCreated());
            outHouse.setState(apply.getState());
            outHouse.setFkAdmin(Integer.valueOf(jwtUtil.getUserId(token)));
            outHouseMapper.insertSelective(outHouse);
        };*/

        OutHouse outHouse1 = outHouseMapper.selectByPrimaryKey(id);
        if(outHouse1 != null){
            outHouse1.setState(state);
            outHouse1.setFkAdmin(Integer.valueOf(jwtUtil.getUserId(token)));
            outHouseMapper.updateByPrimaryKey(outHouse1);
        }else {

            OutHouse outHouse=new OutHouse();
            outHouse.setId(apply.getId());
            outHouse.setOrderNumber(apply.getApplyNumber());
            outHouse.setName(apply.getName());
            outHouse.setCategory(apply.getCategory());
            outHouse.setQuality(apply.getQuality());
            outHouse.setTotal(apply.getTotal());
            outHouse.setFkUser(apply.getFkUser());
            outHouse.setCreated(apply.getCreated());
            outHouse.setState(apply.getState());
            outHouse.setFkAdmin(Integer.valueOf(jwtUtil.getUserId(token)));
            outHouseMapper.insertSelective(outHouse);
        }

        return new Result(Result.CODE_SUCCESS, "操作成功");
    }

    //获取出库信息订单列表
    public Result getListByState(Integer currentPage, Integer pageSize, String name) {
        PageHelper.startPage(currentPage, pageSize);
        //添加模糊查询条件
        Example example=new Example(Apply.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");
        criteria.andNotEqualTo("state","2");
        criteria.andNotEqualTo("state","0");

        List<OutHouse> outHouses = outHouseMapper.selectByExample(example);
        // 获取分页信息
        PageInfo<OutHouse> pageInfo = new PageInfo<>(outHouses);

        List<OutHouseVO> vos = new ArrayList<>();

        for (OutHouse outHouse : outHouses) {
            OutHouseVO vo=new OutHouseVO();
            vo.setId(outHouse.getId());
            vo.setOrderNumber(outHouse.getOrderNumber());
            vo.setName(outHouse.getName());
            vo.setCategory( answerUtils.getCategoryDisplay(outHouse.getCategory()));
            vo.setQuality(answerUtils.getDisplayAnswer(outHouse.getQuality()));
            vo.setTotal(outHouse.getTotal());
            vo.setCreated(outHouse.getCreated());
            vo.setState(outHouse.getState());

            Admin user = adminMapper.selectByPrimaryKey(outHouse.getFkAdmin());
            vo.setAdmin(user);
            Staff staff = staffMapper.selectByPrimaryKey(outHouse.getFkUser());
            vo.setStaff(staff);
            vos.add(vo);
        }

        PageBean pageBean = new PageBean(pageInfo.getTotal(), vos);
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);

    }

    //获取申请列表
    public Result getApplyList(Integer currentPage, Integer pageSize, String name) {
        PageHelper.startPage(currentPage, pageSize);
        //添加模糊查询条件
        Example example=new Example(Apply.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");

        List<OutHouse> outHouses = outHouseMapper.selectByExample(example);
        // 获取分页信息
        PageInfo<OutHouse> pageInfo = new PageInfo<>(outHouses);

        List<OutHouseVO> vos = new ArrayList<>();

        for (OutHouse outHouse : outHouses) {
            OutHouseVO vo=new OutHouseVO();
            vo.setId(outHouse.getId());
            vo.setOrderNumber(outHouse.getOrderNumber());
            vo.setName(outHouse.getName());
            vo.setCategory( answerUtils.getCategoryDisplay(outHouse.getCategory()));
            vo.setQuality(answerUtils.getDisplayAnswer(outHouse.getQuality()));
            vo.setTotal(outHouse.getTotal());
            vo.setCreated(outHouse.getCreated());
            vo.setState(outHouse.getState());

            Admin user = adminMapper.selectByPrimaryKey(outHouse.getFkAdmin());
            vo.setAdmin(user);
            Staff staff = staffMapper.selectByPrimaryKey(outHouse.getFkUser());
            vo.setStaff(staff);
            vos.add(vo);
        }

        PageBean pageBean = new PageBean(pageInfo.getTotal(), vos);
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);

    }

    //确认入库订单收货成功
    @Transactional
    public Result trueApply(Integer id) {
        Apply apply = applyMapper.selectByPrimaryKey(id);
        apply.setState(3);
        applyMapper.updateByPrimaryKey(apply);

        OutHouse outHouse = outHouseMapper.selectByPrimaryKey(id);
        outHouse.setState(3);
        outHouseMapper.updateByPrimaryKey(outHouse);

        Example example1=new Example(UseMaterial.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("name",outHouse.getName());
        criteria1.andEqualTo("category",outHouse.getCategory());
        criteria1.andEqualTo("quality",outHouse.getQuality());
        UseMaterial useMaterial = useMaterialMapper.selectOneByExample(example1);

        Example example=new Example(OurMaterial.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",outHouse.getName());
        criteria.andEqualTo("category",outHouse.getCategory());
        criteria.andEqualTo("quality",outHouse.getQuality());
        OurMaterial ourMaterial = ourMaterialMapper.selectOneByExample(example);

        if (useMaterial !=null ){
            useMaterial.setTotal(useMaterial.getTotal() + outHouse.getTotal());
            useMaterialMapper.updateByPrimaryKeySelective(useMaterial);
            ourMaterial.setTotal(ourMaterial.getTotal()-outHouse.getTotal());
            ourMaterialMapper.updateByPrimaryKeySelective(ourMaterial);
        }
        if (useMaterial==null){
            UseMaterial newUseMaterial=new UseMaterial();
            newUseMaterial.setMaterialNumber(ourMaterial.getMaterialNumber());
            newUseMaterial.setName(outHouse.getName());
            newUseMaterial.setCategory(outHouse.getCategory());
            newUseMaterial.setQuality(outHouse.getQuality());
            newUseMaterial.setTotal(outHouse.getTotal());
            useMaterialMapper.insertSelective(newUseMaterial);
            ourMaterial.setTotal(ourMaterial.getTotal()-outHouse.getTotal());
            ourMaterialMapper.updateByPrimaryKeySelective(ourMaterial);
        }

        List<Integer> truckIds= applyOurTruckMapper.selectByApplyId(id);
        for (Integer truckId : truckIds) {
            OurTruck ourTruck =new OurTruck();
            ourTruck.setId(truckId);
            ourTruck.setState(0);
            ourTruckMapper.updateByPrimaryKeySelective(ourTruck);
        }

        applyOurTruckMapper.deleteByOrderId(id);

        return new Result(Result.CODE_SUCCESS, "操作成功");
    }





}



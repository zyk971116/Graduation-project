package com.jf.school.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.school.bean.PageBean;
import com.jf.school.bean.Result;
import com.jf.school.bean.table.OurMaterial;
import com.jf.school.bean.table.UseMaterial;
import com.jf.school.bean.table.UseMaterialInfo;
import com.jf.school.bean.vo.MaterialQualityVO;
import com.jf.school.mapper.OurMaterialMapper;
import com.jf.school.mapper.UseMaterialInfoMapper;
import com.jf.school.mapper.UseMaterialMapper;
import com.jf.school.utils.AnswerUtils;
import com.jf.school.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.unit.DataUnit;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UseMaterialService {

    @Resource
    UseMaterialMapper useMaterialMapper;

    @Resource
    UseMaterialInfoMapper useMaterialInfoMapper;

    //获取物资列表信息
    public Result list(Integer currentPage, Integer pageSize,String name) {
        PageHelper.startPage(currentPage, pageSize);
        //添加模糊查询条件
        Example example=new Example(UseMaterial.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");

        List<UseMaterial> materials = useMaterialMapper.selectByExample(example);

        PageInfo<UseMaterial> pageInfo = new PageInfo<>(materials);
        List<UseMaterial> vos = new ArrayList<>();
        for (UseMaterial question : materials) {
            UseMaterial vo = new UseMaterial();
            BeanUtils.copyProperties(question, vo);
            // 将答案的逻辑值转换为门面值  1 > A   2 > B
            vo.setQuality(AnswerUtils.getDisplayAnswer(question.getQuality()));
            vo.setCategory(AnswerUtils.getCategoryDisplay(question.getCategory()));
            vos.add(vo);
        }

        PageBean pageBean = new PageBean(pageInfo.getTotal(), vos);
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);
    }


    public Result getQuality(String category, String name) {

        List<MaterialQualityVO> voList =useMaterialMapper.selectQuality(category,name);
        List<MaterialQualityVO> vos = new ArrayList<>();
        for (MaterialQualityVO material : voList) {
            MaterialQualityVO vo = new MaterialQualityVO();
            BeanUtils.copyProperties(material, vo);
            // 将答案的逻辑值转换为门面值  1 > A   2 > B
            vo.setQualityName(AnswerUtils.getDisplayAnswer(material.getQuality()));
            vos.add(vo);
        }

        return new Result(Result.CODE_SUCCESS, "查询成功",vos);

    }

    @Transactional
    public Result useMaterial(Integer useId, Integer useTotal) {
        UseMaterial useMaterial = useMaterialMapper.selectByPrimaryKey(useId);

        UseMaterialInfo useMaterialInfo=new UseMaterialInfo();
        useMaterialInfo.setMaterialNumber(useMaterial.getMaterialNumber());
        useMaterialInfo.setCategory(useMaterial.getCategory());
        useMaterialInfo.setQuality(useMaterial.getQuality());
        useMaterialInfo.setName(useMaterial.getName());
        useMaterialInfo.setTotal(useTotal);
        useMaterialInfo.setCreated(DateUtils.getUnixTimeStamp());
        useMaterialInfoMapper.insertSelective(useMaterialInfo);

        useMaterial.setTotal(useMaterial.getTotal()-useTotal);
        useMaterialMapper.updateByPrimaryKey(useMaterial);
        return new Result(Result.CODE_SUCCESS, "使用成功");
    }

    public Result infoList(Integer currentPage, Integer pageSize, String name) {
        PageHelper.startPage(currentPage, pageSize);
        //添加模糊查询条件
        Example example=new Example(UseMaterialInfo.class);
        example.setOrderByClause("created DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");

        List<UseMaterialInfo> materials = useMaterialInfoMapper.selectByExample(example);

        PageInfo<UseMaterialInfo> pageInfo = new PageInfo<>(materials);
        List<UseMaterialInfo> vos = new ArrayList<>();
        for (UseMaterialInfo question : materials) {
            UseMaterialInfo vo = new UseMaterialInfo();
            BeanUtils.copyProperties(question, vo);
            // 将答案的逻辑值转换为门面值  1 > A   2 > B
            vo.setQuality(AnswerUtils.getDisplayAnswer(question.getQuality()));
            vo.setCategory(AnswerUtils.getCategoryDisplay(question.getCategory()));
            vos.add(vo);
        }

        PageBean pageBean = new PageBean(pageInfo.getTotal(), vos);
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);

    }
}

package com.jf.school.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.school.bean.PageBean;
import com.jf.school.bean.Result;
import com.jf.school.bean.table.OtherMaterial;
import com.jf.school.bean.table.OurMaterial;
import com.jf.school.mapper.OtherMaterialMapper;
import com.jf.school.mapper.OurMaterialMapper;
import com.jf.school.utils.AnswerUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OtherMaterialService {

    @Resource
    OtherMaterialMapper othermaterialMapper;

    //获取物资列表信息
    public Result list(Integer currentPage, Integer pageSize,String name) {
        PageHelper.startPage(currentPage, pageSize);
        //添加模糊查询条件
        Example example=new Example(OtherMaterial.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");

        List<OtherMaterial> materials = othermaterialMapper.selectByExample(example);

        PageInfo<OtherMaterial> pageInfo = new PageInfo<>(materials);
        List<OtherMaterial> vos = new ArrayList<>();
        for (OtherMaterial question : materials) {
            OtherMaterial vo = new OtherMaterial();
            BeanUtils.copyProperties(question, vo);
            // 将答案的逻辑值转换为门面值  1 > A   2 > B
            vo.setQuality(AnswerUtils.getDisplayAnswer(question.getQuality()));
            vo.setCategory(AnswerUtils.getCategoryDisplay(question.getCategory()));
            vos.add(vo);
        }

        PageBean pageBean = new PageBean(pageInfo.getTotal(), vos);
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);
    }

    //添加物资信息
    public Result addMaterial(OtherMaterial material) {
        Integer materialNumber = othermaterialMapper.getLastNumber() + 1;

        material.setMaterialNumber(materialNumber);
        int count = othermaterialMapper.insertSelective(material);
        return new Result(Result.CODE_SUCCESS, "添加成功");

    }

    //物资编辑的信息回显
    public Result get(Integer id) {
//        Material material =materialMapper.selectOneByExample(id);
        OtherMaterial material = othermaterialMapper.selectByPrimaryKey(id);

        return new Result(Result.CODE_SUCCESS, "获取成功", material);
    }

    //物资的更新
    public Result updateById(OtherMaterial material) {
        int count = othermaterialMapper.updateByPrimaryKeySelective(material);
        return new Result(Result.CODE_SUCCESS, "更新成功");
    }


    public Result getMaterByCategory(String category) {
        List<OtherMaterial> materials = othermaterialMapper.selectByCategory(category);

        List<OtherMaterial> vos = new ArrayList<>();
        for (OtherMaterial material : materials) {
            OtherMaterial vo = new OtherMaterial();
            BeanUtils.copyProperties(material, vo);
            // 将答案的逻辑值转换为门面值  1 > A   2 > B
            vo.setQuality(AnswerUtils.getDisplayAnswer(material.getQuality()));
            vos.add(vo);
        }
        return new Result(Result.CODE_SUCCESS, "查询成功",vos);

    }

    public Result getPrice(String category, String name, String quality) {

        Map map = othermaterialMapper.selectPriceAndTotal(category, name, quality);
        return new Result(Result.CODE_SUCCESS, "查询成功",map);
    }
}

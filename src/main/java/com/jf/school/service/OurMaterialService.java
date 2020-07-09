package com.jf.school.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.school.bean.PageBean;
import com.jf.school.bean.Result;
import com.jf.school.bean.table.OurMaterial;
import com.jf.school.bean.vo.MaterialQualityVO;
import com.jf.school.mapper.OurMaterialMapper;
import com.jf.school.utils.AnswerUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OurMaterialService {

    @Resource
    OurMaterialMapper ourMaterialMapper;

    //获取物资列表信息
    public Result list(Integer currentPage, Integer pageSize,String name) {
        PageHelper.startPage(currentPage, pageSize);
        //添加模糊查询条件
        Example example=new Example(OurMaterial.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");

        List<OurMaterial> materials = ourMaterialMapper.selectByExample(example);

        PageInfo<OurMaterial> pageInfo = new PageInfo<>(materials);
        List<OurMaterial> vos = new ArrayList<>();
        for (OurMaterial ourMaterial : materials) {
            OurMaterial vo = new OurMaterial();
            BeanUtils.copyProperties(ourMaterial, vo);
            // 将答案的逻辑值转换为门面值  1 > A   2 > B
            vo.setQuality(AnswerUtils.getDisplayAnswer(ourMaterial.getQuality()));
            vo.setCategory(AnswerUtils.getCategoryDisplay(ourMaterial.getCategory()));
            vos.add(vo);
        }

        PageBean pageBean = new PageBean(pageInfo.getTotal(), vos);
        return new Result(Result.CODE_SUCCESS, "查询成功", pageBean);
    }

    //添加物资信息
    public Result addMaterial(OurMaterial material) {
        Integer materialNumber = ourMaterialMapper.getLastNumber() + 1;

        material.setMaterialNumber(materialNumber);
        int count = ourMaterialMapper.insertSelective(material);
        return new Result(Result.CODE_SUCCESS, "添加成功");

    }

    //物资编辑的信息回显
    public Result get(Integer id) {
//        Material material =materialMapper.selectOneByExample(id);
        OurMaterial material = ourMaterialMapper.selectByPrimaryKey(id);

        return new Result(Result.CODE_SUCCESS, "获取成功", material);
    }

    //物资的更新
    public Result updateById(OurMaterial material) {
        int count = ourMaterialMapper.updateByPrimaryKeySelective(material);
        return new Result(Result.CODE_SUCCESS, "更新成功");
    }

    public Result getMaterByCategory(String category) {
        List<OurMaterial> materials = ourMaterialMapper.selectByCategory(category);
        return new Result(Result.CODE_SUCCESS, "查询成功",materials);

    }


    public Result getQuality(String category, String name) {

        List<MaterialQualityVO> voList =ourMaterialMapper.selectQuality(category,name);
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

    public Result getStock(String category, String name, String quality) {

        Integer total=ourMaterialMapper.selectTotal(category,name,quality);
        return new Result(Result.CODE_SUCCESS, "查询成功",total);
    }
}

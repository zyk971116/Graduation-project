package com.jf.school.service;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.Admin;
import com.jf.school.bean.table.Archive;
import com.jf.school.bean.table.Article;
import com.jf.school.bean.table.UseMaterialInfo;
import com.jf.school.bean.vo.Home;
import com.jf.school.bean.vo.UseMaterialVO;
import com.jf.school.mapper.*;
import com.jf.school.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    @Resource
    UseMaterialInfoMapper useMaterialInfoMapper;

    @Resource
    OrderMapper orderMapper;

    @Resource
    ApplyMapper applyMapper;

    @Resource
    ArticleMapper articleMapper;

    @Resource
    ArchiveMapper archiveMapper;


    public Result getInfo() throws ParseException {
        Integer orderTotal = orderMapper.selectTotal();
        Integer applyTotal = applyMapper.selectTotal();
        Integer noticeTotal = articleMapper.selectTotal();

        List<Article> articles1 = articleMapper.selectByState();

        List<Archive> archives = archiveMapper.selectByState();
        for (Archive archive : archives) {
            Long first= DateUtils.getFirstDayOfMoth(archive.getDate());
            Long last= DateUtils.getLastDayOfMoth(archive.getDate());
            //根据开始和结束时间戳查询文章列表
            List<Article> articles=articleMapper.getArticlesByMonth(first, last);
            archive.setArticles(articles);
        }

        List<UseMaterialVO> VOS= useMaterialInfoMapper.selectGroup();
        for (UseMaterialVO vo : VOS) {
            Long first= DateUtils.getFirstDayOfMoth(vo.getDate());
            Long last= DateUtils.getLastDayOfMoth(vo.getDate());
            List<Integer> number=useMaterialInfoMapper.getNumbersByMonth(first, last);
            List<UseMaterialInfo> vos=new ArrayList<>();
            for (Integer integer : number) {
                Integer total=useMaterialInfoMapper.selectByNumber(first, last,integer);
                UseMaterialInfo useMaterialInfo=useMaterialInfoMapper.selectMaterial(integer);
                useMaterialInfo.setTotal(total);
                vos.add(useMaterialInfo);
            }
            vo.setUseMaterialInfo(vos);
        }

        String number= useMaterialInfoMapper.selectGroupUse();
        Long first= DateUtils.getFirstDayOfMoth(number);
        Long last= DateUtils.getLastDayOfMoth(number);
        Float oneInfo= useMaterialInfoMapper.getTotalByMonth(first, last,1);
        Float twoInfo= useMaterialInfoMapper.getTotalByMonth(first, last,2);
        Float threeInfo= useMaterialInfoMapper.getTotalByMonth(first, last,3);


//        Float oneInfo= useMaterialInfoMapper.getOneInfo();
//        Float twoInfo= useMaterialInfoMapper.getTwoInfo();
//        Float threeInfo= useMaterialInfoMapper.getThreeInfo();
        Float total = oneInfo + twoInfo + threeInfo;
        Float one = oneInfo / total;
        Float two = twoInfo/total;
        Float three=threeInfo/total;

        BigDecimal one1 =new BigDecimal(one);
        one1 = one1.setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal two1 =new BigDecimal(two);
        two1 = two1.setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal three1 =new BigDecimal(three);
        three1 = three1.setScale(2,BigDecimal.ROUND_HALF_UP);

        Home home=new Home();
        home.setOrderTotal(orderTotal);
        home.setApplyTotal(applyTotal);
        home.setNoticeTotal(noticeTotal);
        home.setArchive(archives);
        home.setArticle(articles1);
        home.setOne(one1);
        home.setTwo(two1);
        home.setThree(three1);
        home.setMaterialVOS(VOS);

        return new Result(Result.CODE_SUCCESS, "获取成功",home);
    }
}

package com.jf.school.mapper;


import com.jf.school.bean.table.Article;
import com.jf.school.bean.table.Money;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface ArticleMapper extends Mapper<Article> {

    Integer selectTotal();

    List<Article> getArticlesByMonth(Long first, Long last);

    List<Article> selectByState();
}

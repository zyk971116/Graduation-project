package com.jf.school.controller.admin;

import com.jf.school.bean.Result;
import com.jf.school.bean.table.Article;
import com.jf.school.bean.table.Order;
import com.jf.school.service.ArticleService;
import com.jf.school.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/admin/article")
public class ArticleController {

    @Resource
    ArticleService articleService;

    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result list(@PathVariable Integer currentPage, @PathVariable Integer pageSize){
        return articleService.list(currentPage, pageSize);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Article article){
        return articleService.add(article);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
    return articleService.findById(id);
}

//    @DeleteMapping("/{id}")
//    public Result deleteArticleById(@PathVariable Integer id){
//        return articleService.deleteArticleById(id);
//    }

}

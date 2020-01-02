package com.baizhi.controller;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {
//    注入service
    @Autowired
    private ArticleService articleService;

//    分页
    @ResponseBody
    @RequestMapping("articleQueryPage")
    public Map<String,Object> articleQueryPage(Integer page,Integer rows){
        Map<String, Object> map = articleService.queryPage(page, rows);
        return map;
    }
//    jqgrid  添加  修改  删除
    @RequestMapping("articleEdit")
    public Map<String,Object> articleEdit(Article article, String oper, String[] id){
        Map<String, Object> map = new HashMap<>();
        if("del".equals(oper)){
//            调用方法
            int i = articleService.plRemove(id);
 //            判断
            if(0 != i){
                map.put("msg","删除成功");
            }else{
                map.put("msg","删除失败");
            }
        }
        return map;
    }
//    添加
    @RequestMapping("add")
    public Map<String,Object> add(Article article){
        Map<String, Object> add = articleService.add(article);
        return add;
    }
//    修改
    @RequestMapping("modify")
    public int modify(Article article){
        int modify = articleService.modify(article);
        System.out.println("-----------------------对象为："+article);
        return modify;
    }
//    根据id查询
    @RequestMapping("queryById")
    public Article queryById(String id){
        Article article = articleService.queryById(id);
        return article;
    }
//          文件上传
    @RequestMapping("articleUpload")
    public Map<String,Object> articleUpload(MultipartFile img, HttpServletRequest request, HttpSession session){
        Map<String, Object> map = articleService.articleUpload(img, request, session);
        return map;
    }
//    图片空间
    @RequestMapping("getAllImgs")
    public Map<String,Object> getAllImgs(HttpServletRequest request){
        Map<String, Object> map = articleService.getAllImgs(request);
        return map;
    }
}

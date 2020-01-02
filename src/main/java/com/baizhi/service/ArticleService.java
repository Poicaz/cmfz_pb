package com.baizhi.service;

import com.baizhi.entity.Article;
import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ArticleService {
//    分页查询
    public Map<String ,Object> queryPage(Integer page,Integer rows);
//    批量删除
    public int plRemove(String[] id);
//    添加
    public Map<String,Object> add(Article article);
//    修改
    public int modify(Article article);
//    根据id查询
    public Article queryById(String id);
//    上传
    public Map<String,Object> articleUpload(MultipartFile img, HttpServletRequest request, HttpSession session);
//    图片空间
    public Map<String,Object> getAllImgs(HttpServletRequest request);
}

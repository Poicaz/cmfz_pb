package com.baizhi.dao;

import com.baizhi.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface First_PageDao {
//    轮播图
    public List<Banner> header();
//    专辑
    public List<Album> album();
//    文章
    public List<Article> article();
//    章节
    public List<Chapter> wen();
}

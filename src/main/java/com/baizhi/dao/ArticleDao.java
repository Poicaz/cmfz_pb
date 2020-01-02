package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao {
//    分页查询
    public List<Article> selectPage(@Param("start") Integer start, @Param("rows") Integer rows);
//    总条数查询
    public Integer selectCount();
//    批量删除
    public int plDelete(String[] id);
//    添加
    public int insert(Article article);
//    修改
    public int update(Article article);
//    根据id查询
    public Article selectById(String id);
}

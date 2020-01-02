package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BannerDao {
//    查询所有
    public List<Banner> selectAll();
//    添加
    public void insert(Banner banner);
//    修改
    public void update(Banner banner);
//    批量删除
    public void PlDelete(String[] id);
//    分页查询
    public List<Banner> selectByPage(@Param("start") Integer start, @Param("rows") Integer rows);
//    查询总数
    public Integer selectCount();
}

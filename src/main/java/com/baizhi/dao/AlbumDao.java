package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AlbumDao {
//    分页查询
    public List<Album> selectPage(@Param("start") Integer start, @Param("rows") Integer rows);
//    查询总数量
    public Integer selectCount();
}

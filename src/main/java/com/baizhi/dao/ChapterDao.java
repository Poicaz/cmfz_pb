package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterDao {
//    分页查询
    public List<Chapter> selectPage(@Param("start") Integer start, @Param("rows") Integer rows,@Param("album_id") String album_id);
//    查询总数量
    public Integer selectCount(@Param("album_id") String album_id);
//      添加
    public int insert(Chapter chapter);
//      修改
    public int update(Chapter chapter);
//    批量删除
    public int  plDelete(String[] id);

}

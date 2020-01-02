package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao {
//    查询用户名
    public Admin selectByUsernameAndPassword(@Param("username") String username);
//    查询所有
    public List<Admin> selectAll();
}

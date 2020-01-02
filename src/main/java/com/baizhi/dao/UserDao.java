package com.baizhi.dao;

import com.baizhi.entity.UserDto;
import com.baizhi.entity.UserDtos;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {
//    查询最近7天注册的用户
    public List<UserDto> selectByDate();
//查询1-12月注册用户量
    public List<UserDto> selectByMonth();
//    统计用户地理分布图
    public List<UserDtos> selectByAddress();
}

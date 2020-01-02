package com.baizhi.service;

import com.baizhi.entity.UserDto;
import com.baizhi.entity.UserDtos;

import java.util.List;

public interface UserDtoService {
    //    查询最近7天注册的用户
    public List<Integer> queryByDate();
    //查询1-12月注册用户量
    public List<UserDto> queryByMonth();
    //    统计用户地理分布图
    public List<UserDtos> queryByAddress();
}

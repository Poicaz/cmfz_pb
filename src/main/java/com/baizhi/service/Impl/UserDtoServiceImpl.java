package com.baizhi.service.Impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.UserDto;
import com.baizhi.entity.UserDtos;
import com.baizhi.service.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class UserDtoServiceImpl implements UserDtoService {
@Autowired
private UserDao userDao;
//查询最近7天注册的用户
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Integer> queryByDate() {
        List<Integer> list1 = new ArrayList<>();
        List<UserDto> list = userDao.selectByDate();
        for (UserDto userDto : list) {
            list1.add(userDto.getTotal());
        }
        return list1;
    }
//查询1-12月注册用户量
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserDto> queryByMonth() {
        return userDao.selectByMonth();
    }
//统计用户地理分布图
//统计用户地理分布图
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserDtos> queryByAddress() {
        return userDao.selectByAddress();
    }
}

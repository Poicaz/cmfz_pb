package com.baizhi;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.entity.UserDto;
import com.baizhi.entity.UserDtos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestUserDto {
@Autowired
private UserDao userDao;
//查询最近7天中注册的用户
    @Test
    public void test01(){
        List<UserDto> list = userDao.selectByDate();
        for (UserDto userDto : list) {
            System.out.println(userDto);
        }
    }
//    查询1-12月注册用户量
    @Test
    public void test02(){
        List<UserDto> list = userDao.selectByMonth();
        for (UserDto userDto : list) {
            System.out.println(userDto);
        }
    }
//    统计用户地理分布图
    @Test
    public void test03(){
        List<UserDtos> list = userDao.selectByAddress();
        for (UserDtos userDtos : list) {
            System.out.println(userDtos);
        }
    }
}

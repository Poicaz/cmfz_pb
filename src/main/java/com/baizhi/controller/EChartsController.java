package com.baizhi.controller;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.UserDto;
import com.baizhi.entity.UserDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("ECharts")
public class EChartsController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("getECharts")
    public List<Integer> getECharts(){
        List<Integer> list = new ArrayList<>();
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        return list;
    }
    @RequestMapping("getMap")
    public List<Map<String,Object>> getMap(){
        List<Map<String,Object>> list = new ArrayList<>();
        return null;
    }
//    SELECT * FROM 表名 where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(时间字段名)
//    SELECT address,count(address) FROM t_user GROUP BY address
//
}

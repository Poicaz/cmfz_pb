package com.baizhi.controller;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.UserDto;
import com.baizhi.entity.UserDtos;
import com.baizhi.service.UserDtoService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("userDto")
public class UserDtoController {
    @Autowired
    private UserDtoService userDtoService;
    @RequestMapping("queryByDate")
    public List<Integer> queryByDate(){
        List<Integer> list = userDtoService.queryByDate();
        return list;
    }
    @RequestMapping("queryByMonth")
    public List<UserDto> queryByMonth(){
        List<UserDto> list = userDtoService.queryByMonth();
        return list;
    }
    @RequestMapping("queryByAddress")
    public List<UserDtos> queryByAddress(){
        List<UserDtos> list = userDtoService.queryByAddress();
        return list;
    }
}

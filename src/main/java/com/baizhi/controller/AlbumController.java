package com.baizhi.controller;

import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
//    注入service
    @Autowired
    private AlbumService albumService;
//    分页查询
    @RequestMapping("queryPage")
    public Map<String ,Object> queryPage(Integer page,Integer rows){
        Map<String, Object> map = albumService.queryPage(page, rows);
        return map;
    }
}

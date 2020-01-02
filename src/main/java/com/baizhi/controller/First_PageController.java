package com.baizhi.controller;

import com.baizhi.service.First_PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping
public class First_PageController {
    @Autowired
    private First_PageService first_pageService;
    @RequestMapping("first_page")
    public Map<String,Object> first_page(String uid, String type, String sub_type, HttpServletRequest request){
        Map<String, Object> map = first_pageService.first_page(uid, type, sub_type, request);
        return map;
    }
}

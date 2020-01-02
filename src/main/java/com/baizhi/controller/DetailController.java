package com.baizhi.controller;

import com.baizhi.service.First_PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("detail")
public class DetailController {
    @Autowired
    private First_PageService first_pageService;

    @RequestMapping("wen")
    public Map<String,Object> wen(String id, String uid){
        Map<String, Object> wen = first_pageService.wen(id, uid);
        return wen;
    }
}

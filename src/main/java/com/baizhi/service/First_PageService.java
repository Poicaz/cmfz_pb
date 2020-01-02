package com.baizhi.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface First_PageService {
    public Map<String,Object> first_page(String uid, String type, String sub_type, HttpServletRequest request);
    public Map<String,Object> wen(String id,String uid);
}

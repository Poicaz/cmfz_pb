package com.baizhi.service;

import java.util.Map;

public interface AlbumService {
//    分页查询
    public Map<String,Object> queryPage(Integer page,Integer rows);
}

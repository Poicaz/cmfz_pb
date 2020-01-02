package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
//    查询所有
    public List<Banner> queryAll();
//    添加
    public void add(Banner banner);
//    修改
    public Map<String,Object> modify(Banner banner);
//分页查询
    public Map<String,Object> queryPage(Integer page,Integer rows);
//    批量删除
    public void plRemove(String[] id);
}

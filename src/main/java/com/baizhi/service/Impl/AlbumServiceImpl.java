package com.baizhi.service.Impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
//    注入dao接口
    @Autowired
    private AlbumDao albumDao;
//    分页查询
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryPage(Integer page, Integer rows) {
        /*
        * page:当前页
        * rows:每页展示的条数
        * records:总条数
        * total:总页数
        * */
//        计算起始条数
        Integer start = (page - 1)*rows;
//        查询起始条数和每页展示条数
        List<Album> albums = albumDao.selectPage(start, rows);
        System.out.println(albums);
//        查询总条数
        Integer records = albumDao.selectCount();
//        计算总页数
        Integer totalPage = records%rows==0 ? records/rows : records/rows+1;
        Map<String , Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",albums);
        map.put("records",records);
        map.put("total",totalPage);
        return map;
    }
}

package com.baizhi.service.Impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    //    查询所有
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Banner> queryAll() {
        List<Banner> banners = bannerDao.selectAll();
        return banners;
    }

    //添加
    @Override
    public void add(Banner banner) {
//        为id设置uuid
        banner.setId(UUID.randomUUID().toString());
        bannerDao.insert(banner);
    }

    //修改
    @Override
    public Map<String,Object> modify(Banner banner) {
        Map<String, Object> map = new HashMap<>();
//        判断修改的图片是否为空或者是否为空字串
        if(banner.getImg()==null||banner.getImg().equals("")){
            banner.setImg(null);
            map.put("id","");
            bannerDao.update(banner);
        }else {
            bannerDao.update(banner);
            map.put("id", banner.getId());
        }
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryPage(Integer page, Integer rows) {
        /*
         * page:当前页数
         * rows:数据
         * records:总条数
         * total:总页数
         * */
//        计算每一页的起始条数
        Integer start = (page - 1) * rows;
//        查询数据
        List<Banner> banners = bannerDao.selectByPage(start, rows);
//        查询总条数
        Integer records = bannerDao.selectCount();
//        计算总页数
        Integer totalPage = records % rows == 0 ? records / rows : records / rows + 1;
        Map<String, Object> map = new HashMap<>();
//        页数
        map.put("page", page);
//        展示的数据
        map.put("rows", banners);
//        总页数
        map.put("total", totalPage);
//        总条数
        map.put("records", records);
        return map;
    }

    //  批量删除
    @Override
    public void plRemove(String[] id) {
        bannerDao.PlDelete(id);
    }
}

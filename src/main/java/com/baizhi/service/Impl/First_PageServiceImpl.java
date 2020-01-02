package com.baizhi.service.Impl;

import com.baizhi.dao.First_PageDao;
import com.baizhi.entity.*;
import com.baizhi.service.First_PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class First_PageServiceImpl implements First_PageService{
    @Autowired
    private First_PageDao first_pageDao;


    @Override
    public Map<String, Object> first_page(String uid, String type, String sub_type, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
//        http
            String scheme = request.getScheme();
//        localhost
            InetAddress localHost = InetAddress.getLocalHost();
            String localhost = localHost.toString().split("/")[1];
//          PC-20190718ZLAM/192.168.1.156  获取192.168.1.156
            int serverPort = request.getServerPort();
//            获取项目名
            String contextPath = request.getContextPath();
            if("all".equals(type)){
                List<Banner> header = first_pageDao.header();
                List<Object> list = new ArrayList<>();
//                添加数据进map
                for (Banner banner : header) {
                    HeaderDto headerDto = new HeaderDto();
                    headerDto.setThumbnail(scheme+"://"+localhost+":"+serverPort+contextPath+"/upload/img"+banner.getImg());
                    headerDto.setDesc(banner.getTitle());
                    headerDto.setId(banner.getId());
                    list.add(headerDto);
                }
                map.put("header",list);
//                专辑
                List<Album> album = first_pageDao.album();
                List<Object> list1 = new ArrayList<>();
                for (Album album1 : album) {
                    BodyDto bodyDto = new BodyDto();
                    bodyDto.setThumbnail(scheme+"://"+localhost+":"+serverPort+contextPath+"/upload/img"+album1.getImg());
                    bodyDto.setTitle(album1.getTitle());
                    bodyDto.setAuthor("");
                    bodyDto.setType("0");
                    bodyDto.setSet_count(album1.getCount());
                    bodyDto.setCreate_date(album1.getCreate_date());
                    list1.add(bodyDto);
                }
               map.put("body",list1);
//                文章
                /*List<Article> article = first_pageDao.article();
                ArrayList<Object> list2 = new ArrayList<>();
                for (Article article1 : article) {
                    BodysDto bodysDto = new BodysDto();
                    bodysDto.setThumbnail("");
                    bodysDto.setType(article1.getTitle());
                    bodysDto.setAuthor(article1.getAuthor());
                    bodysDto.setType("1");
                    bodysDto.setSet_count("");
                    bodysDto.setCreate_date(article1.getCreate_date());
                    list2.add(bodysDto);
                }
                map.put("body",list2);*/
            }else if ("wen".equals(type)){
                List<Album> album = first_pageDao.album();
                List<Object> list3 = new ArrayList<>();
                for (Album album1 : album) {
                    BodyDto bodyDto = new BodyDto();
                    bodyDto.setThumbnail(scheme+"://"+localhost+":"+serverPort+contextPath+"/upload/img"+album1.getImg());
                    bodyDto.setTitle(album1.getTitle());
                    bodyDto.setAuthor("");
                    bodyDto.setType("0");
                    bodyDto.setSet_count(album1.getCount());
                    bodyDto.setCreate_date(album1.getCreate_date());
                    list3.add(bodyDto);
                }
                map.put("body",list3);
            }else if ("si".equals(type)){
                List<Article> article = first_pageDao.article();
                ArrayList<Object> list4 = new ArrayList<>();
                for (Article article1 : article) {
                    BodysDto bodysDto = new BodysDto();
                    bodysDto.setThumbnail("");
                    bodysDto.setType(article1.getTitle());
                    bodysDto.setAuthor(article1.getAuthor());
                    bodysDto.setType("1");
                    bodysDto.setSet_count("");
                    bodysDto.setCreate_date(article1.getCreate_date());
                    list4.add(bodysDto);
                }
                map.put("body",list4);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> wen(String id, String uid) {
        Map<String, Object> map = new HashMap<>();
        List<Album> album = first_pageDao.album();
//        数据
        for (Album album1 : album) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("thumbnail",album1.getImg());
            map1.put("title",album1.getTitle());
            map1.put("score",album1.getScore());
            map1.put("author",album1.getAuthor());
            map1.put("broadcast",album1.getBroadCaster());
            map1.put("set_count",album1.getCount());
            map1.put("brief",album1.getBrief());
            map1.put("create_date",album1.getCreate_date());
            map.put("introduction",map1);
        }
        List<Object> list1 = new ArrayList<>();
        List<Chapter> wen = first_pageDao.wen();
//        数据
        for (Chapter chapter : wen) {
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("title",chapter.getTitle());
            hashMap.put("download_url",chapter.getSrc());
            hashMap.put("size",chapter.getSize());
            hashMap.put("duration",chapter.getDuration());
            list1.add(hashMap);
        }
        map.put("list",list1);

        return map;
    }
}

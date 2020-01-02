package com.baizhi;

import com.baizhi.dao.First_PageDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestDto {
    @Autowired
    private First_PageDao first_pageDao;
    @Test
    public void test01(){
        List<Banner> header = first_pageDao.header();
        for (Banner banner : header) {
            System.out.println(banner);
        }
    }
    @Test
    public void test02(){
        List<Album> album = first_pageDao.album();
        for (Album album1 : album) {
            System.out.println(album1);
        }
    }
    @Test
    public void test03(){
        List<Chapter> wen = first_pageDao.wen();
        for (Chapter chapter : wen) {
            System.out.println(chapter);
        }
    }
}

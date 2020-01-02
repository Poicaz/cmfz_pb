package com.baizhi;

import com.baizhi.dao.AlbumDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestAlbum {
    @Autowired
    private AlbumDao albumDao;

    @Test
    public void test01(){
        Integer count = albumDao.selectCount();
        System.out.println(count);
    }
}

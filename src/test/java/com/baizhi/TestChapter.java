package com.baizhi;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestChapter {
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private ChapterService chapterService;
    @Test
    public void test(){
        Map<String, Object> map = chapterService.queryPage(3, 2, "1");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"-----------"+entry.getValue());
        }
    }
    @Test
    public void test01(){
        Integer integer = chapterDao.selectCount("2");
        System.out.println(integer);
    }
    @Test
    public void test02(){
        List<Chapter> chapters = chapterDao.selectPage(0, 2,"1");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }
    @Test
    public void test03(){
        int i = chapterDao.insert(new Chapter("5", "Youngblood", "2", "3.22MB", "3分23秒", "5 Seconds Of Summer - Youngblood.mp3", "正常"));
        System.out.println(i);
    }
    @Test
    public void test04(){
        int i = chapterDao.update(new Chapter("5", "I Knew You Were Trouble.", "2", "3.38MB", "3分39秒", "Taylor Swift - I Knew You Were Trouble..mp3", "正常"));
        System.out.println(i);
    }
    @Test
    public void test05(){
        String[] ids ={"3"};
        int i = chapterDao.plDelete(ids);
        System.out.println("删除成功"+i);
    }
}

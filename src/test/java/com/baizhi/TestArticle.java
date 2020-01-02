package com.baizhi;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestArticle {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleService articleService;
//    分页
    @Test
    public void test01(){
        List<Article> articles = articleDao.selectPage(0, 1);
        for (Article article : articles) {
            System.out.println(article);
        }
    }
//    查询总数量
    @Test
    public void test02(){
        Integer integer = articleDao.selectCount();
        System.out.println(integer);
    }
//    批量删除

    @Test
    public void test03(){
        String[] ids ={"5"};
        int i = articleDao.plDelete(ids);
        System.out.println("删除成功"+i);
    }
//    添加
    @Test
    public void test04(){
        int i = articleDao.insert(new Article("6", "金刚经", "鸠摩罗什", "金刚即金刚石，世间最坚硬的物质，般若，指世间至高无上的大智慧，波罗蜜，佛家指彼岸，全名的意思是指以无上的大智慧洞彻尘世间虚妄的本质，超脱苦难，到达彼岸", "1", new Date(), "正常"));
        System.out.println("添加陈宫"+i);
    }
//    修改
    @Test
    public void test05(){
        int update = articleDao.update(new Article("6", "金刚经", "鸠摩罗什", "金刚即金刚石，世间最坚硬的物质，般若，指世间至高无上的大智慧，波罗蜜，佛家指彼岸，全名的意思是指以无上的大智慧洞彻尘世间虚妄的本质，超脱苦难，到达彼岸", "1", null, "冻结"));
        System.out.println("--------修改成功："+update);
    }
//   根据id查询
    @Test
    public void test06(){
        Article article = articleDao.selectById("6");
        System.out.println("查询结果为："+article);
    }
    //   根据id查询
    @Test
    public void test07(){
        Article article = articleService.queryById("6");
        System.out.println("查询结果为："+article);
    }
}

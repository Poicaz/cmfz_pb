package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestBanner {
    @Autowired
    private BannerDao bannerDao;
//    添加
    @Test
    public void test01(){
    bannerDao.insert(new Banner("2","图2","img/shouye.jpg",new Date(),"显示"));
    System.out.println("---添加成功--");
    }
    //    查询
    @Test
    public void test02(){
        List<Banner> banners = bannerDao.selectAll();
        for (Banner banner : banners) {
            System.out.println(banner);
        }
    }
//    修改
    @Test
    public void test03(){
      bannerDao.update(new Banner("2","图片2","img/captcha.jpg",new Date(),"不显示"));
        System.out.println("修改成功");
    }
//    查询总数
    @Test
    public void test04(){
        Integer integer = bannerDao.selectCount();
        System.out.println(integer);
    }
//    批量删除
    @Test
    public void test05(){
        String[] ids ={"2"};
        bannerDao.PlDelete(ids);
        System.out.println("批量删除成功");
    }
//    easypoi
    @Test
    public void test06(){
        List<Banner> banners = bannerDao.selectAll();
        for (Banner banner : banners) {
//            读出图片的路径+图片的名字     图片的路径
            banner.setImg("E:\\last_project\\last_project\\cmfz_pb\\src\\main\\webapp\\upload\\img\\"+banner.getImg());
        }
//        ExportParam中的 参数一:标题    参数二：表名
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图","轮播图"),
                Banner .class, banners);
        try {
//            写出
            workbook.write(new FileOutputStream("E:/banners.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test07(){

    }
}

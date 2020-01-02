package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Banner;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TestClass {
    @Autowired
private AdminDao adminDao;
    @Autowired
    private BannerDao bannerDao;
    @Autowired
private AdminService adminService;


    @Test
    public void test1() {
        Admin admin = adminDao.selectByUsernameAndPassword("admin");
        System.out.println(admin);
    }
    @Test
    public void test02(){
        String queryByUsernameAndPassword = adminService.queryByUsernameAndPassword("admin", "admin");
        System.out.println(queryByUsernameAndPassword);
    }
//    查询所有
    @Test
    public void test03(){
        List<Admin> admins = adminDao.selectAll();
        for (Admin admin : admins) {
            System.out.println(admin);
        }
    }


}

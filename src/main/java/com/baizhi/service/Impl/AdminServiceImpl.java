package com.baizhi.service.Impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
//    注入dao
    @Autowired
    private AdminDao adminDao;
//    根据用户名查询所有信息
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public String queryByUsernameAndPassword(String username, String password) {
        System.out.println(username);
        System.out.println(password);
//        调用方法
        Admin admin = adminDao.selectByUsernameAndPassword(username);
        System.out.println("-------------用户为："+admin);
        if(admin!=null){
            System.out.println("------------密码为："+admin.getPassword());
          if(password.equals(admin.getPassword())){
              return "success";
          }else{
              return "密码错误";
          }
        }else{
            return "该用户不存在";
        }
    }

}

package com.baizhi.controller;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminDao adminDao;
//    登录
    @RequestMapping("login")
    public String login(String username, String  password, String code, HttpSession session){
//        获取存取在作用域中的验证码
        String  securityCode = (String) session.getAttribute("securityCode");
//        判断输入的验证码是否和获取的验证码一致
        if(code.equals(securityCode)){
//            一致  就调用查询用户名和密码
            String admin = adminService.queryByUsernameAndPassword(username, password);
//            将用户名存取到作用域中
            session.setAttribute("username",username);
            return admin;
        }else{
            return "验证码错误";
        }
    }
//    poi查询所有
    @RequestMapping("queryAll")
    public List<Admin> queryAll(){
        return  adminDao.selectAll();
    }
//    poi导出
    @RequestMapping("poiOut")
    public void poiOut(HttpServletResponse response){
//        查询所有
        List<Admin> admins = adminDao.selectAll();
//        创建文件
        HSSFWorkbook workbook = new HSSFWorkbook();
//        创建字体
        HSSFFont font = workbook.createFont();
//        设置字体大小
        font.setFontHeightInPoints((short)10);
//        设置文字颜色
        font.setColor(Font.COLOR_RED);
//        设置字体加粗
        font.setBold(true);
//        设置字体
        font.setFontName("微软雅黑");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
//        创建工作簿
        HSSFSheet sheet = workbook.createSheet("管理员表");
//        创建行
        HSSFRow row = sheet.createRow(0);
//        自定义标题
        String[] titles = {"编号","用户名","密码"};
//        遍历
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title);
            cell.setCellStyle(cellStyle);
        }
//        对查出来的值进行遍历
        for (int i = 0; i < admins.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(admins.get(i).getId());
            row1.createCell(1).setCellValue(admins.get(i).getUsername());
            row1.createCell(2).setCellValue(admins.get(i).getPassword());
        }
        try {
//            设置请求头
            response.setHeader("content-disposition","attachment;filename=admin.xls");
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

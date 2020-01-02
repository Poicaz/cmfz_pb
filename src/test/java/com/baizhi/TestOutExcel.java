package com.baizhi;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestOutExcel {
    @Autowired
    private BannerDao bannerDao;

    @Test
    public void test01(){
//        创建文件
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
//        创建字体
        HSSFFont font = workbook.createFont();
//        将字体设置为B粗体
        font.setBold(true);
//        设置字体的高度
        font.setFontHeightInPoints((short)10);
//        设置日期格式
        cellStyle.setDataFormat(format);
        cellStyle.setFont(font);
//        创建工作簿  表
        HSSFSheet sheet = workbook.createSheet();

//        设置表的宽度
        sheet.setColumnWidth(0,70*256);
//        创建行
        HSSFRow row = sheet.createRow(0);
//        创建单元格
        HSSFCell cell = row.createCell(0);

//        单元格设置值
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);
//        写出
        try {
            workbook.write(new File("E:/test01.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() throws IOException {
//        创建excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
//        创建dataFormat
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);

//        创建字体
        HSSFFont font = workbook.createFont();
//        设置字体大小
        font.setFontHeightInPoints((short) 10);
//        设置字体
        font.setFontName("微软雅黑");
//        设置字体加粗
        font.setBold(true);
//        设置字体颜色
        font.setColor(Font.COLOR_RED);
//        设置样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
//        设置字体居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
//        创建工作簿
        HSSFSheet sheet = workbook.createSheet("文章表");
//        设置长度
        sheet.setColumnWidth(4,20*256);
//        创建行
        HSSFRow row = sheet.createRow(0);
//        自定义标题行
        String[] titles = {"编号","标题","图片","日期","状态"};
        for (int i = 0;i<titles.length;i++){
            String title = titles[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title);
//            给标题设置样式
            cell.setCellStyle(cellStyle);
        }
        //        查询所有
        List<Banner> banners = bannerDao.selectAll();
        for (int i = 0; i < banners.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(banners.get(i).getId());
            row1.createCell(1).setCellValue(banners.get(i).getTitle());
            row1.createCell(2).setCellValue(banners.get(i).getImg());
            row1.createCell(3).setCellValue(banners.get(i).getCreate_date());
            row1.createCell(4).setCellValue(banners.get(i).getStatus());
            HSSFCell cell = row1.createCell(4);
            cell.setCellValue(banners.get(i).getCreate_date());
            cell.setCellStyle(cellStyle1);
        }
        workbook.write(new File("E:/banner.xls"));
        workbook.close();
    }
}

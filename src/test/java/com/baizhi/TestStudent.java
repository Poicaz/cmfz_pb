package com.baizhi;

import com.baizhi.dao.StudentDao;
import com.baizhi.entity.Student;
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
public class TestStudent {
    @Autowired
    private StudentDao studentDao;

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
        String[] titles = {"编号","姓名","年龄","性别","生日"};
        for (int i = 0;i<titles.length;i++){
            String title = titles[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title);
//            给标题设置样式
            cell.setCellStyle(cellStyle);
        }
        //        查询所有
        List<Student> students = studentDao.selectAll();
        for (int i = 0; i < students.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(students.get(i).getId());
            row1.createCell(1).setCellValue(students.get(i).getName());
            row1.createCell(2).setCellValue(students.get(i).getAge());
            row1.createCell(3).setCellValue(students.get(i).getSex());
            row1.createCell(4).setCellValue(students.get(i).getBir());
//            对时间格式化
            HSSFCell cell = row1.createCell(4);
            cell.setCellValue(students.get(i).getBir());
            cell.setCellStyle(cellStyle1);
        }
        workbook.write(new File("E:/student.xls"));
        workbook.close();
    }
    @Test
    public void test01() throws IOException {
//        创建文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyy-MM-dd");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
//        创建工作簿
        HSSFSheet sheet = workbook.createSheet("myCat");
//        创建行
        HSSFRow row = sheet.createRow(0);
//        单元格
        HSSFCell cell = row.createCell(0);
//        设置值
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);
//        写出
        workbook.write(new File("E:/myCat.xls"));
        workbook.close();
    }
//
}

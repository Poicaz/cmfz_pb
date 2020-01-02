package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget("bannerEntity")
public class Banner implements Serializable {
    @Excel(name = "编号")
    private String id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "图片",type = 2,width = 30,height = 40,imageType = 1)
    private String img;
    @Excel(name = "创建时间",databaseFormat = "yyyyMMddhhmmss",format = "yyyy-MM-dd")
    private Date create_date;
    @Excel(name = "状态")
    private String status;
}

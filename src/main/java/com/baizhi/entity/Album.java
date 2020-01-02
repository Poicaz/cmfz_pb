package com.baizhi.entity;

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
public class Album implements Serializable {
    private String id;
    private String title;//标题
    private String img;//专辑图片
    private String score;//专辑评分
    private String author;//作者
    private String broadCaster;//播音
    private String count;//章节数量
    private String brief;//简介
    private Date create_date;//专辑创建时间
    private String status;//专辑状态
}


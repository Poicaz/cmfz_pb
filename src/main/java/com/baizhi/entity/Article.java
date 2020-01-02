package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private String id;
    private String title;//标题
    private String author;//作者
    private String content;//内容
    private String guru_id;//上师id
    private Date create_date;//创建日期
    private String status;//状态
}

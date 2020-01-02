package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String id;
    private String phone_number;//电话号码
    private String password;//密码
    private String name;//名字
    private String dharma;//法名
    private String head_img;//头像
    private String sex;//性别
    private String address;//地址
    private String sign;//签名
    private String guru_id;//上师id
    private Date last_date;//最后一次登录的时间
    private Date create_date;//注册的时间
    private String status;//状态
    private String salt;//盐值
}

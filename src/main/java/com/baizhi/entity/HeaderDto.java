package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderDto implements Serializable {
    private String thumbnail;//图片路径
    private String desc;//标题
    private String id;//id
}

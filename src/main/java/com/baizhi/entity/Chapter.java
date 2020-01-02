package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter implements Serializable {
    private String id;
    private String title;//标题
    private String album_id;//专辑id
    private String size;//章节大小
    private String duration;//时长
    private String src;//音频路径
    private String status;//音频状态
}

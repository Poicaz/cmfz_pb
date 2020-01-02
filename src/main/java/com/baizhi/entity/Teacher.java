package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget("teacherEntity")
public class Teacher {
    @Excel(name = "编号")
    private String id ;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "科目")
    private String project;
    private List<Student> list;
}

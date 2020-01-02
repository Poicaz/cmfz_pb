package com.baizhi.dao;

import com.baizhi.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentDao {
    public List<Student> selectAll();
}

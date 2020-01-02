package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ChapterService {
//分页查询
    public Map<String,Object> queryPage(Integer page,Integer rows,String album_id);
//    添加
    public Map<String ,Object> add(Chapter chapter);
//    修改
    public int modify(Chapter chapter);
//    批量删除
    public int plRemove(String[] id);
//    文件上传
    public void chapterUpload(MultipartFile src, String id, HttpSession session);
//    文件下载
    public void chapterDownload(String fileName, HttpServletRequest request, HttpServletResponse response);
}

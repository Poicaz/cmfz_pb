package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController {
//    注入service
    @Autowired
    private ChapterService chapterService;

//    分页查询
    @RequestMapping("queryByPage")
    public Map<String,Object> queryByPage(Integer page,Integer rows,String album_id){
        Map<String, Object> map = chapterService.queryPage(page,rows,album_id);
        return map;
    }
//    jqgrid  添加修改 删除
    @RequestMapping("chapterEdit")
    public Map<String ,Object> chapterEdit(Chapter chapter, String oper, String[] id){
        Map<String, Object> map = new HashMap<>();
        if("add".equals(oper)){
//            添加
            Map<String, Object> map1 = chapterService.add(chapter);
            if(map1.get("i").equals(1)){
                map.put("msg","添加成功");
                map.put("id",map1.get("id"));
            }else{
                map.put("msg","添加失败");
            }
        }else if("edit".equals(oper)){
//            判断用户是否修改音频
            if(chapter.getSrc()==null||chapter.getSrc().equals("")) {
//                用户没有修改音频
                System.out.println("-----文件路径：------"+chapter.getSrc());
                chapter.setSrc(null);
                //            修改
                chapterService.modify(chapter);
                map.put("msg","修改成功");
            }else{
                //            修改
                chapterService.modify(chapter);
                map.put("msg","修改成功");
                map.put("id",chapter.getId());
            }
            //            删除
            }else if("del".equals(oper)){
//            调用方法
            int i = chapterService.plRemove(id);
//            判断
            if(0 != i){
            map.put("msg","删除成功");
            }else{
             map.put("msg","删除失败");
            }
        }
        return map;
        }
//        文件上传
    @ResponseBody
    @RequestMapping("chapterUpload")
    public void chapterUpload(MultipartFile src, String id, HttpSession session){
        System.out.println("---------文件id为：-------"+id);
//        调用方法
        chapterService.chapterUpload(src,id,session);
    }

//    文件下载
    @RequestMapping("chapterDownload")
    public void chapterDownload(String src, HttpServletRequest request, HttpServletResponse response){
//        调用业务方法
    chapterService.chapterDownload(src,request,response);
    }

    //    获取文件时长
    static Long encoder(File file){
        Encoder encoder = new Encoder();
        long ls = 0;
        MultimediaInfo m;
        try {
            m = encoder.getInfo(file);//这里传入的是文件对象
            ls = m.getDuration()/1000;//得到一个long类型的时长
        } catch (EncoderException e) {
            e.printStackTrace();
            System.out.println("获取音频时长有误:"+e.getMessage());
        }
        return ls;
    }

}

package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {
    //    注入service
    @Autowired
    private BannerService bannerService;
    @Autowired
    private BannerDao bannerDao;

    //    查询
    @RequestMapping("queryAll")
    public List<Banner> queryAll() {
        List<Banner> banners = bannerService.queryAll();
        return banners;
    }

    //jqgrid edit 添加 修改 批量删除
    @ResponseBody
    @RequestMapping("edit")
    public Map<String,Object> edit(Banner banner, String oper, String[] id) {
        Map<String, Object> map = null;
//        添加
        if ("add".equals(oper)) {
            bannerService.add(banner);
//            修改
        } else if ("edit".equals(oper)) {
           map = bannerService.modify(banner);
//            删除
        } else if ("del".equals(oper)) {
            bannerService.plRemove(id);
        }
        return map;
    }

    //    分页查询
    @RequestMapping("queryPage")
    public Map<String, Object> queryPage(Integer page, Integer rows) {
        Map<String, Object> map = bannerService.queryPage(page, rows);
        return map;
    }

    //上传
    @ResponseBody
    @RequestMapping("upload")
    public Banner upload(MultipartFile img,String id, HttpSession session) {
//      获得upload路径
        String path = session.getServletContext().getRealPath("/upload/img");
        File file = new File(path);
//        判断上传文件夹是否存在
        if (!file.exists()) {
            file.mkdirs();
        }
//        获取文件真实名字
        String filename = img.getOriginalFilename();
        System.out.println("---------------filename:"+filename);
//        为了防止同一个文件多次上传发生覆盖 拼接时间戳
        String name = new Date().getTime() + "_" + filename;
        System.out.println("----------名字："+name);
//       文件上传
        try {
            img.transferTo(new File(path, name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //        修改图片的路径
        Banner banner = new Banner();
//        设置要修改的id
        banner.setId(id);
//        设置修改的图片的名字
        banner.setImg(name);
        System.out.println("-------------名字为："+banner);
        bannerService.modify(banner);
        return banner;
    }
    @RequestMapping("easyPoi")
    public void easyPoi(HttpServletResponse response){
        List<Banner> banners = bannerDao.selectAll();
        for (Banner banner : banners) {
//            获取图片路径
            banner.setImg("E:\\last_project\\last_project\\cmfz_pb\\src\\main\\webapp\\upload\\img\\"+banner.getImg());
        }
//        创建excel
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图", "轮播图"), Banner.class, banners);
        try {
//            设置请求头
            response.setHeader("content-disposition","attachment;filename=banner.xls");
            ServletOutputStream outputStream = response.getOutputStream();
            //        写出
            workbook.write(outputStream);
//            关流
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

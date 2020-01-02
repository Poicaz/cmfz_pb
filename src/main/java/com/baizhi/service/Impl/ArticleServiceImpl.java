package com.baizhi.service.Impl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
//    注入dao
@Autowired
private ArticleDao articleDao;

//    分页查询
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryPage(Integer page, Integer rows) {
        /*
        * page:当前页
        * rows:每页展示的数据
        * records:总条数
        * total:总页数
        * */
//        计算起始条数
        Integer start = (page-1)*rows;
//       查询起始条数
        List<Article> articles = articleDao.selectPage(start, rows);
//        查询总条数
        Integer records = articleDao.selectCount();
//        计算总页数
        Integer total = records%rows==0 ? records/rows : records/rows+1;
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",articles);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

//    批量删除
    @Override
    public int plRemove(String[] id) {
        int i = articleDao.plDelete(id);
        return i;
    }
//  添加
    @Override
    public Map<String,Object> add(Article article) {
        Map<String, Object> map = new HashMap<>();
        String id = UUID.randomUUID().toString();
        article.setId(id);
        int i = articleDao.insert(article);
        map.put("i",i);
        map.put("id",article.getId());
        return map;
    }
//  修改
    @Override
    public int modify(Article article) {
        int i = articleDao.update(article);
        return i;
    }
//  根据id查询
    @Override
    public Article queryById(String id) {
        Article article = articleDao.selectById(id);
        return article;
    }

    @Override
    public Map<String,Object> articleUpload(MultipartFile img, HttpServletRequest request, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
//    根据相对路径获取绝对路径
        String realPath = session.getServletContext().getRealPath("/upload/img/");
//        创建一个新的文件
        File file = new File(realPath);
//        判断文件是否存在
        if(!file.exists()){
//            没有就创建
            file.mkdirs();
        }
//        获取文件真实名字
        String filename = img.getOriginalFilename();
//        为了防止同一个文件多次上传发生覆盖  拼接时间戳
        String name = new Date().getTime()+"_"+filename;
        try {
//        文件上传
            img.transferTo(new File(realPath,name));
            //没有错误
            map.put("error",0);
            //获取http
            String scheme = request.getScheme();
            //获取localhost
            InetAddress localHost = InetAddress.getLocalHost();
            //PC-20190718ZLAM/192.168.1.156  获取192.168.1.156
            String localhost = localHost.toString().split("/")[1];
            //获取port
            int serverPort = request.getServerPort();
//           获取 项目名
            String contextPath = request.getContextPath();
//            拼接路径
           String url =  scheme+"://"+localhost+":"+serverPort+contextPath+"/upload/img/"+name;
//           将拼接的文件添加筋map集合
           map.put("url",url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /*
 *
 * {
 "moveup_dir_path": "",
 "current_dir_path": "",
 "current_url": "/ke4/php/../attached/",
 "total_count": 5,
 "file_list": [
     {
         "is_dir": false,
         "has_file": false,
         "filesize": 208736,
         "dir_path": "",
         "is_photo": true,
         "filetype": "jpg",
         "filename": "1241601537255682809.jpg",
         "datetime": "2018-06-06 00:36:39"
     }
  ]
}
 * */
//          图片空间
    @Override
    public Map<String, Object> getAllImgs(HttpServletRequest request) {
        Map<String , Object> map = new HashMap<>();
//        通过相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/img/");
//        创建新的文件
        File file = new File(realPath);
//        获取文件夹中的所有图片以及格式和路径
        String[] imgs = file.list();
//        创建list数组
        ArrayList<Object> list = new ArrayList<>();
//      遍历imgs数组
        for (String img : imgs) {
            Map<String, Object> hashMap = new HashMap<>();
            //是不是目录
            hashMap.put("is_dir",false);
//            是不是有文件
            hashMap.put("has_file",false);
//          获取文件的大小
            File file1 = new File(realPath, img);
            long length = file1.length();
//            文件大小
            hashMap.put("filesize",length);
//            目录的路径
            hashMap.put("dir_path","");
//            是不是图片
            hashMap.put("is_photo",true);
//            返回资源名的路径
            String extension = FilenameUtils.getExtension(img);
//            文件的类型
            hashMap.put("filetype",extension);
//            文件名
            hashMap.put("filename",img);
//            切分文件获取上传时间
            String s = img.split("_")[0];
//            把string类型的时间转换为long类型
            Long aLong = Long.valueOf(s);
//            把long类型赋时间转换为Data类型
            Date date = new Date(aLong);
//            转换日期格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            将日期格式转换为string类型
            String format = simpleDateFormat.format(date);
//            上传时间
            hashMap.put("datetime",format);
//            将map集合添加进list集合中
            list.add(hashMap);
        }
//        将list集合添加进map集合中
        map.put("file_list",list);
        map.put("moveup_dir_path","");
        map.put("current_dir_path","");
        /*
         * http://localhost:80/cmfz/upload/img
         * */
        try {
 //          获取http
            String scheme = request.getScheme();
//          获取localhost
            InetAddress localHost = InetAddress.getLocalHost();
//          PC-20190718ZLAM/192.168.1.156  获取192.168.1.156
            String localhost = localHost.toString().split("/")[1];
//            获取port
            int serverPort = request.getServerPort();
//            获取项目名
            String contextPath = request.getContextPath();
//            拼接路径
            String  urlPath = scheme+"://"+localhost+":"+serverPort+contextPath+"/upload/img/";
//            文件保存路径
            map.put("current_url",urlPath);
//             文件总数
            map.put("total_count",imgs.length);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return map;
    }
}

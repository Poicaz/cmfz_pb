package com.baizhi.service.Impl;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
//    注入Dao
    @Autowired
    private ChapterDao chapterDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryPage(Integer page, Integer rows,String album_id) {
        /*
        * page:当前页
        * rows:当前页展示的数据
        * records:总条数
        * total:总页数
        * */
//        计算起始条数
        Integer start = (page-1) * rows;
//        查询起始条数
        List<Chapter> chapters = chapterDao.selectPage(start,rows,album_id);
//      查询总条数
        Integer records = chapterDao.selectCount(album_id);
//        计算总页数
        Integer totalPage = records%rows==0 ? records/rows : records/rows+1;
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",chapters);
        map.put("records",records);
        map.put("total",totalPage);
        return map;
    }
//      添加
    @Override
    public Map<String, Object> add(Chapter chapter) {
        Map<String, Object> map = new HashMap<>();
        String id = UUID.randomUUID().toString();
        chapter.setId(id);
        int i = chapterDao.insert(chapter);
        map.put("i",i);
        map.put("id",chapter.getId());
        return map;
    }
    //      修改
    @Override
    public int  modify(Chapter chapter) {
        int i = chapterDao.update(chapter);
        return i;
    }
//      删除
    @Override
    public int plRemove(String[] id) {
        int i = chapterDao.plDelete(id);
        return i;
    }

//    文件上传
    @Override
    public void chapterUpload(MultipartFile src, String id, HttpSession session) {
//    通过相对路径获取绝对路径
        String realPath = session.getServletContext().getRealPath("/audio");
//        创建文件
        File file = new File(realPath);
//        判断文件是否存在
        if(!file.exists()){
            file.mkdirs();
        }
//        获取文件真实名字
        String filename = src.getOriginalFilename();
//        为了防止同一个文件多次上传发生覆盖  拼接时间戳
        String name = new Date().getTime()+"_"+filename;
//        文件上传
        try {
            src.transferTo(new File(realPath,name));
//            获得时长
            AudioFile read = AudioFileIO.read(new File(realPath, name));
            AudioHeader audioHeader = read.getAudioHeader();
            int trackLength = audioHeader.getTrackLength();
            String seconde = trackLength%60+"秒";
            String minute = trackLength/60+"分";
//            大小
           String size =  src.getSize()/1024/1024 +"MB";
//            修改
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setDuration(minute+seconde);
            chapter.setSize(size);
            chapter.setSrc(name);
            chapterDao.update(chapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//      文件下载
    @Override
    public void chapterDownload(String src, HttpServletRequest request, HttpServletResponse response) {
//      通过相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/audio");
//        创建一个文件
        File file = new File(realPath, src);
        /*
        * src ---> 1577259937256_4.mp3    4.mp3
        * */
        String name = src.split("_")[1];
        ServletOutputStream outputStream = null;
//        以附件形式下载  设置响应头
        try {
//            为文件名（中文）进行编码操作
            String encode = URLEncoder.encode(name, "UTF-8");
//            响应数据前设置下载方式  以及下载后的文件名
            response.setHeader("content-disposition","attachment;filename="+encode);
            outputStream = response.getOutputStream();
            FileUtils.copyFile(file,outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
*   接受数据   文件标识  文件名
//        调用业务   从服务器上获取对应文件  /然后响应给客户端
//        获取文件？文件路径（通过相对路径获取绝对路径）  IO流输入
        String path = request.getServletContext().getRealPath("/audio");
        FileInputStream is = null;
        ServletOutputStream os = null;
        try {
//            等价于    path+"/"+fileName
            is = new FileInputStream(new File(path, src));
            String newFileName = src.split("_")[1];
//            设置响应类型  显影的文件类型
//            获取文件类型通过文件名获取文件后缀
            String houzhui = FilenameUtils.getExtension(src);
            System.out.println("文件后缀为"+houzhui);
//            在通过后缀获取MIME类型
            String mimeType = request.getSession().getServletContext().getMimeType("." + houzhui);
            response.setContentType(mimeType);

//        通过响应输出流给Client响应数据
            os = response.getOutputStream();

//        为文件名（中文）进行编码操作
            String newSrc = URLEncoder.encode(newFileName, "UTF-8");
//            响应数据前设置下载方式  以及下载后的文件名
            response.setHeader("content-disposition","attachment;src="+newSrc);

            //        文件传输  读取过程中给客户端响应数据
            byte[] bytes = new byte[2048];
            while (true){
//                返回值代表读取数据的个数  如果到达文件末尾  返回-1
                int i = is.read(bytes, 0, bytes.length);
                if (i==-1)break;
//                向外响应
                os.write(bytes,0,i);
            }
//            关闭资源
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
* */
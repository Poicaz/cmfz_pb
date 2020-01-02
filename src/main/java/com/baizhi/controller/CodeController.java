package com.baizhi.controller;

import com.baizhi.util.ValidateImageCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("code")
public class CodeController {
    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response, HttpSession session){
//        获取验证码的字符
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
//        将获取到的验证码的字符存储到作用域中
        session.setAttribute("securityCode",securityCode);
//        获取图片
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
//        以流的方式打到页面
        ServletOutputStream outputStream=null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image,"png",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

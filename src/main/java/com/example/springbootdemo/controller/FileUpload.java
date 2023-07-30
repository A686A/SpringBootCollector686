package com.example.springbootdemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
public class FileUpload {

    @PostMapping("/upload")
    public String up(String nickname, MultipartFile photo, HttpServletRequest request) throws IOException {
        System.out.println(nickname);
        // 获取图片的原始名称
        System.out.println(photo.getOriginalFilename());
        // 取文件类型
        System.out.println(photo.getContentType());
       //浏览器输入localhost：8080/filename  可以直接看到文件  只在当此进程生效
       String path = request.getServletContext().getRealPath("/upload/");
       //如何访问上传的文件？？？  file:///D://pic/upload1.jpg  浏览器访问此地址也可以拿到照片
        //String path = "D:\\pic\\upload";
        System.out.println(path);
        saveFile(photo,path);
        return "上传成功";
    }

    //
    public void saveFile(MultipartFile photo,String path) throws IOException {
//       判断存储的目录是否存在，如果不存在则创建
        File dir = new File(path);
        if(!dir.exists()){
//          创建目录
            dir.mkdir();
        }

        File file = new File(path+photo.getOriginalFilename());
        photo.transferTo(file);
    }
}
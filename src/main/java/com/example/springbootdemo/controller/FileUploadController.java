package com.example.springbootdemo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    //http://localhost:8080/upload    在postman-body中修改上传content-type类型为multipart/form-data（选择form）key列选择file
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public String upFile(String nickname, MultipartFile photo, HttpServletRequest request) throws IOException {
        System.out.println(nickname);
        // 获取图片的原始名称
        System.out.println(photo.getOriginalFilename());
        // 取文件类型
        System.out.println(photo.getContentType());
        //case1   浏览器输入localhost：8080/filename  可以直接看到文件  只在当此进程生效  此地址为自动随机地址
        String path = request.getServletContext().getRealPath("/static/upload/");
        //case 2   如何访问上传的文件？？？  file:///D://pic/upload1.jpg  浏览器访问此地址也可以拿到照片
        //String path = "D:\\pic\\upload";
        System.out.println(path);
        saveFile(photo, path);
        return "上传成功";
    }

    public void saveFile(MultipartFile photo, String path) throws IOException {
        //判断存储的目录是否存在，如果不存在则创建
        File dir = new File(path);
        if (!dir.exists()) {
            //创建目录
            dir.mkdir();
        }

        File file = new File(path + photo.getOriginalFilename());
        photo.transferTo(file);
    }

    public void csvOutput() {

    }
    public void delete() {
        String path = "D:/testdemo";
//     File file = new File(path);
//     file.delete();

        // 删除指定文件夹
        FileSystemUtils.deleteRecursively(new File(path));
    }
}

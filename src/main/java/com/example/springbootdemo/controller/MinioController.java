package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.untils.MinioUtil;
import com.example.springbootdemo.config.MinioConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class MinioController {
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private MinioConfig prop;

    //http://localhost:8080/get
    @GetMapping("/get")
    public String getUrl() {
        String uuid = UUID.randomUUID().toString();
        return minioUtil.createUploadUrl("", "");
    }


    //http://localhost:8080/get
    @GetMapping("/getUrl")
    public String get() {
        return minioUtil.preview("");
    }

    //http://localhost:8080/upload
    @PostMapping("/upload222")
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        String objectName = minioUtil.upload(file);
        if (null != objectName) {
            return prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName;
        }
        return "fail";
    }
}

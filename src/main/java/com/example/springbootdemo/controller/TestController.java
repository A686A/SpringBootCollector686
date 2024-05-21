package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Book;
import com.example.springbootdemo.service.Impl.TestImpl;
import com.example.springbootdemo.service.TestService;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    TestImpl test;

    @GetMapping("/Testone")
    public void ListRestTemplate() {
        String path = "D:/testdemo";
//     File file = new File(path);
//     file.delete();

        // 删除指定文件夹
        FileSystemUtils.deleteRecursively(new File(path));
    }

    @GetMapping("/tan")
    public String TRestTemplate() {

        Book book = new Book();
        String a = book.getAuthor().toString();
        System.out.println(a);
        return "aaa";
    }

    @GetMapping("/trn")
    public void TesRestTemplate() {

        test.tan();
    }

    @GetMapping("/testException")
    public void TestException( String a) {

        test.tan();
    }
}

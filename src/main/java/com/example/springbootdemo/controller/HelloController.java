package com.example.springbootdemo.controller;


import com.example.springbootdemo.entity.Group;
import com.example.springbootdemo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    @GetMapping("/hello")
//http://localhost:8080/hello
    public String hello() {

        return "hhah sunrui";
    }

    @PostMapping("/helloUser")
//http://localhost:8080/helloUser
    public String helloUser(String userName, String passWord) {
        System.out.println("hhah sunrui" + userName + passWord);
        return "hhah sunrui" + userName + passWord;
    }


    @PostMapping("/helloUser2")
//http://localhost:8080/helloUser2
    public String helloUser2(User user) {
        System.out.println(user);
        System.out.println(user.getUsername());
        return "hhah sunrui" + user;
    }



}
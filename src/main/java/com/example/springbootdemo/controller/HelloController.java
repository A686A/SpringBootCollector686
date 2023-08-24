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


    //http://localhost:8080/MockMvcTest
    @GetMapping("/MockMvcTest")
    public String MockMvcTest() {
        return "MockMvcTest";
    }

    //http://localhost:8080/RestTemplateGroup
    @GetMapping("/RestTemplateGroup/{userNo}")
    public Group RestTemplateGroup(@PathVariable String userNo) {

        System.out.println(userNo);
        Group group =new Group();
        List<User> users = new ArrayList<>();
        User user =new User();
        user.setId(1);
        user.setUsername("sun");
        user.setBirthday("19970920");
        users.add(user);
        group.setUserList(users);
        return group;
    }
}
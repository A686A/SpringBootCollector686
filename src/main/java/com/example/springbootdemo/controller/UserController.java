package com.example.springbootdemo.controller;


import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper usermapper;

    @GetMapping("/user")
    public List<User> query(){
        List<User> userList = usermapper.find();
        return userList;
    }

    @PostMapping("/insertUser")
    public int insertUser(User user){
        usermapper.insert(user);
        return 1;
    }
}

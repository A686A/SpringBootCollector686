package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestTemplateController {
    @Autowired
    RestTemplateService restTemplateService;

    //http://localhost:8080/RestTemplateList
    @GetMapping("/RestTemplateList")
    public List<User> restTemplateList(){

        return restTemplateService.restTemplateList();
    }
}

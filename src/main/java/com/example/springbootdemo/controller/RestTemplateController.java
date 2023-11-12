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

    //http://localhost:8080/ListRestTemplate
    @GetMapping("/ListRestTemplate")
    public List<User> ListRestTemplate() {

        return restTemplateService.listRestTemplate();
    }

    //http://localhost:8080/ListRestTemplate
    @GetMapping("/PostTemplateList")
    public List<User> PostTemplateList() {

        return restTemplateService.listRestTemplate();
    }
}

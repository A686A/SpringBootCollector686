package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.Impl.RestTemplateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestTemplateController {
    @Value("${url.testtemplateurl}")
    String urltest;
    @Autowired
    RestTemplateServiceImpl restTemplateServiceImpl;


    //http://localhost:8080/RestTemplateTest
    @GetMapping("/RestTemplateTest")
    public String restTemplateTest(){
         return restTemplateServiceImpl.RestTemplate();
        }



}

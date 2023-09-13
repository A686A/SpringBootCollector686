package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.entity.Group;

import com.example.springbootdemo.service.RestTemplateService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
public class RestTemplateController {
    @Value("${url.testtemplateurl}")
    String urltest;
    @Autowired
    RestTemplateService restTemplateService;


    //http://localhost:8080/RestTemplateTest
    @GetMapping("/RestTemplateTest")
    public String restTemplateTest(){
         return restTemplateService.RestTemplate();
        }



}

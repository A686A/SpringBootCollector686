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

/**
 *resttemplate
 */

@RestController
public class RestTemplateController {
    @Value("${url.testtemplateurl}")
    String urltest;
    @Autowired
    RestTemplateService restTemplateService;


    @GetMapping("/RestTemplate")
    public Group restTemplate(){
        HttpHeaders httpHeaders =new HttpHeaders();
        httpHeaders.set("accseeToken","213");

        HttpEntity httpEntity =new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        String userNO ="2";

            String url=urltest+userNO;
            ResponseEntity<Group>  responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity,Group.class);

            System.out.println("status==="+responseEntity.getStatusCode().value());
            System.out.println("body====="+responseEntity.getBody());
            Integer backstatus =new Integer(responseEntity.getStatusCode().value());
            Integer nofind =new Integer(404);
            if(nofind.equals(backstatus)){
                System.out.println("hello");
            }

            return responseEntity.getBody();
    }

    //http://localhost:8080/RestTemplateTest
    @GetMapping("/RestTemplateTest")
    public String restTemplateTest(){
         return restTemplateService.RestTemplate();
        }

    //http://localhost:8080/RestTemplateDemo
    @GetMapping("/RestTemplateDemo")
    public String restTemplateDemo(){

        return null;
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

        return null;
    }
}

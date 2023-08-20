package com.example.springbootdemo.controller;

import com.example.springbootdemo.handler.CustomErrorHandler;
import com.example.springbootdemo.entity.Group;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *resttemplate
 */

@RestController
public class RestTemplateController {
    @Value("${url.testtemplateurl}")
    String urltest;

    @GetMapping("/RestTemplate")
    public Group restTemplate(){
        HttpHeaders httpHeaders =new HttpHeaders();
        httpHeaders.set("accseeToken","213");
        HttpEntity httpEntity =new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new CustomErrorHandler());
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
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders =new HttpHeaders();
        //httpHeaders.set("accseeToken","213");
        HttpEntity httpEntity =new HttpEntity<>(httpHeaders);
            String apiURL = "https://www.mxnzp.com/api/weather/current/深圳市?app_id=jspdgus&app_secret=8h";
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiURL, HttpMethod.GET,httpEntity,String.class);

            if (200 == responseEntity.getStatusCodeValue()) {
                return responseEntity.getBody();
            } else {
                return "error with code : " + responseEntity.getStatusCodeValue();
            }
        }
}

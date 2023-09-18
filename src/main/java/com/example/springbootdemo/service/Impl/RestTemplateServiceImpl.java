package com.example.springbootdemo.service.Impl;

import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {
    @Value("${Acount.APP_ID}")
    String appId;
    @Value("${Acount.APP_SECRET}")
    String appSecret;

    public String RestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);
        String apiURL = "https://www.mxnzp.com/api/weather/current/深圳市?app_id=" + appId + "&app_secret=" + appSecret;
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiURL, HttpMethod.GET, httpEntity, String.class);

        if (200 == responseEntity.getStatusCodeValue()) {
            return responseEntity.getBody();
        } else {
            return "error with code : " + responseEntity.getStatusCodeValue();
        }
    }

    //RestTemplate返回值为List<User>
    public List<User> restTemplateList(){
        RestTemplate restTemplate = new RestTemplate();
        String apiURL="http://localhost:8080/user";
        ParameterizedTypeReference<List<User>> responseBodyType = new ParameterizedTypeReference<List<User>>() {};
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(apiURL, HttpMethod.GET, null, responseBodyType);
        return responseEntity.getBody();
    }
}

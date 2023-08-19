package com.example.springbootdemo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
/*
* 23.8.18 解决testtemplate不返回http error code 问题  重写了hasError方法  在RestTemplateController中使用
* */

public class CustomErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
//        if (response.getStatusCode() == HttpStatus.OK){
//            return false;
//        }
        return true;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }
}

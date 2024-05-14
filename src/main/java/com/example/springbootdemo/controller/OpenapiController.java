package com.example.springbootdemo.controller;

import com.example.demo.api.UsersApi;
import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OpenapiController implements UsersApi {
    @Override
    public ResponseEntity<List<User>> usersGet() {
        return UsersApi.super.usersGet();
    }
}

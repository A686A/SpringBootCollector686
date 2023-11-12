package com.example.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/Testone")
    public int ListRestTemplate() {

        int a = 3;
        int b = 4;
        if (a == 3) {
            b = 5;
        }
        System.out.println(b);
        return b;
    }
}

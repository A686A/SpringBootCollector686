package com.example.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    //rsources/templates/index.html
    @GetMapping("/")
    public String index() {
        return "index";
    }
}

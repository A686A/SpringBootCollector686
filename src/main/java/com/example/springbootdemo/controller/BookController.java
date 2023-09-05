package com.example.springbootdemo.controller;


import com.example.springbootdemo.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookMapper bookMapper;
    @GetMapping("/book")
//http://localhost:8080/book

    public List book() {
        return bookMapper.getBook();
    }


    @GetMapping("/bookgroup")
//http://localhost:8080/book

    public List bookgroup() {
        return bookMapper.getGroup();
    }
}

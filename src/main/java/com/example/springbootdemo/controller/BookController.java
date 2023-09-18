package com.example.springbootdemo.controller;


import com.example.springbootdemo.entity.Group;
import com.example.springbootdemo.entity.groupVo;
import com.example.springbootdemo.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //http://localhost:8080/bookgroup
    public List<Group> bookgroup() {
bookMapper.getGroup();
        return null;
    }
}

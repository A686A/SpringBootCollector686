package com.example.springbootdemo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Group {
    String groupName;
    List<Book> bookList;
}

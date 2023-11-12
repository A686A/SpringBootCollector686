package com.example.springbootdemo.entity;

import lombok.Data;

@Data
public class Employee {
    private Long id;
    private String name;
    private int age;
    private int sex;
}
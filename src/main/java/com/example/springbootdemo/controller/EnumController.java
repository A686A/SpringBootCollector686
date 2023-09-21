package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.CategoryEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnumController {
    @GetMapping("Enm")
    public void testOne() {

        CategoryEnum[] categories = CategoryEnum.values();

        System.out.println(CategoryEnum.HISTORY.name());

        for (CategoryEnum categoryEnum : categories) {
            System.out.println(categoryEnum);
        }
    }
}

package com.example.springbootdemo.entity;


public enum CategoryEnum {
    HISTORY("his"),
    SCIENCE("science");

    String value;
    CategoryEnum(String value){
        this.value=value;
    }

}

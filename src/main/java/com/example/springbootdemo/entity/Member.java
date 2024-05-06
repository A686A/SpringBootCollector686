package com.example.springbootdemo.entity;

import lombok.Data;

@Data
public class Member {
    private int memberId;
    private int teamId;
    private String memberName;
    private String position;
}

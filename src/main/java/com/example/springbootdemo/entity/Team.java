package com.example.springbootdemo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Team {
    private int teamId;
    private int groupId;
    private String teamName;
    private List<Member> memberList;
}

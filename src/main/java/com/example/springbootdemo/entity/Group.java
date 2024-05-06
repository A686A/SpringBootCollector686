package com.example.springbootdemo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Group {
    private int id;
    private String groupName;
    private List<Team> teamList;
}

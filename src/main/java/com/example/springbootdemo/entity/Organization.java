package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Map;

@Data
//@TableName（table-name）  类名与表名不一致的情况下使用此标签  table标签只在使用basemapper的时候有用，自己写sql没用
@TableName("organization")
public class Organization {

    private String id;

    private String name;

    private String description;

    private String remark;

    @TableField(exist = false)
    private Map<String, Member> members;
}

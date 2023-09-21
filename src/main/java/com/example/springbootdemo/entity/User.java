package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
//@TableName（table-name）  类名与表名不一致的情况下使用此标签  table标签只在使用basemapper的时候有用，自己写sql没用
public class User {
    //@TableId(type = IdType.AUTO)  设置主键自增
    private int id;
    //@TableField("table-coumlon-name") 表里的字段名和类的属性名不一致
    private String username;
    private String password;
    private String birthday;
    //@TableField(exist = false)此字段表里不一定百分之百有
    @TableField(exist = false)
    private List<Order> orders;

}

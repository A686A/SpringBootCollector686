package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

//@TableName（table-name）  类名与表名不一致的情况下使用此标签  table标签只在使用basemapper的时候有用，自己写sql没用
public class User {
    //@TableId(type = IdType.AUTO)  设置主键自增
    private  int id;
    //@TableField("table-coumlon-name") 表里的字段名和类的属性名不一致
    private String username;
    private String password;
    //@TableField(exist = false)此字段表里不一定百分之百有

    private String birthday;
    @TableField(exist = false)
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

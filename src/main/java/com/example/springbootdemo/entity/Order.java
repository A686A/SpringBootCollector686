package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class Order {
    private int id;
    private String orderDate;
    private String total;
    @TableField(exist = false)
    private int uid;
}

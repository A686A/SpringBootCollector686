package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("member")
public class Member {
    private String id;
    @TableField("organization_id")
    private String organizationId;
    private String username;
    private String email;
    @TableField("created_at")
    private String createdAt;

    @TableField(exist = false)
    private String memberId;
    @TableField(exist = false)
    private String teamId;
    @TableField(exist = false)
    private String memberName;
    @TableField(exist = false)
    private String position;
}

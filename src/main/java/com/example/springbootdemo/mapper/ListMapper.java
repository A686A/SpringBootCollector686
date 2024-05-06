package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.Group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListMapper {
    List<Group> getGroupInfo();
}

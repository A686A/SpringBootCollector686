package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

    /**
     * 添加
     */
    int save(Employee employee);

    /**
     * 添加临时表
     */
    int saveTemp(Employee employee);
}

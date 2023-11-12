package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BatchMapper {

    /**
     * 添加
     */
    int save(Employee employee);

    /**
     * 添加临时表
     * @param employee
     * @return
     */
    int saveTemp(Employee employee);


    /**
     * 清空数据
     */
    void truncateAll();

    /**
     * 清空临时表数据
     */
    void truncateTemp();
}

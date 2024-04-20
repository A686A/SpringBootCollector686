package com.example.springbootdemo.service.Impl;

import com.example.springbootdemo.entity.Employee;
import com.example.springbootdemo.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class TestImpl {

    @Autowired
    TestMapper testMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void tan() {
        Employee employee = new Employee();
        employee.setAge(100);
        employee.setName("www");
        testMapper.save(employee);
        throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
    }
}

package com.example.springbootdemo.service.Impl;

import com.example.springbootdemo.entity.Employee;
import com.example.springbootdemo.mapper.TestMapper;
import com.example.springbootdemo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TestMapper testMapper;
    @Autowired
    TestImpl test;

    public void exc() {
        throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
    }

    @Override
    @Transactional
    public void tantest() {

        Employee employee = new Employee();
        employee.setAge(20);
        employee.setName("mmmm");
        testMapper.saveTemp(employee);
        test.tan();

    }
}

package com.example.springbootdemo.service.Impl;

import com.example.springbootdemo.entity.Group;
import com.example.springbootdemo.mapper.ListMapper;
import com.example.springbootdemo.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Service
public class ListServiceImpl implements ListService {
    @Autowired
    ListMapper listMapper;

    @Override
    public List<Group> getGroupInfo() {
        int a = 5;
        if (a < 6) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "xxxxxhelloce");
        }

        return listMapper.getGroupInfo();
    }
}

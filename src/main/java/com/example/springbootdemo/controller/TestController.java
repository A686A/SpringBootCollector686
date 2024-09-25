package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.constant.CodeConst;
import com.example.springbootdemo.common.constant.MessageId;
import com.example.springbootdemo.common.log.SLogger;
import com.example.springbootdemo.common.log.SLoggerFactory;
import com.example.springbootdemo.entity.Book;
import com.example.springbootdemo.service.Impl.TestImpl;
import com.example.springbootdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    TestImpl test;
    private static final SLogger LOGGER = SLoggerFactory.getLogger(TestController.class);

    @GetMapping("/testLog")
    public void LogTest() {
        System.out.println("LoginUserRole" + CodeConst.USER_ROLE);
        LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), "error");
    }
    @GetMapping("/dataSet")
    public void DataSetTest() {

        LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId());
    }
}

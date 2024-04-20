package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.Impl.TestImpl;
import com.example.springbootdemo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    TestImpl testService;

    //http://localhost:8080/transaction
    @GetMapping("/tans")
    public void TRestTemplate() {
        testService.tan();
//        transactionService.tantest();
    }
}

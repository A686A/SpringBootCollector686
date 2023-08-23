package com.example.springbootdemo.controller;

import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {
    void MockMvcTest(){
        Random random = Mockito.mock(Random.class);
    }

}
package com.example.springbootdemo;

import com.example.springbootdemo.service.ThreadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest()
public class ThreadControllerTest {
    @Autowired
    ThreadService threadService;
    @Test
    void testThread(){
        threadService.thread();
    }

    @Test
    void testCompletableFuture() throws ExecutionException, InterruptedException {
        threadService.completableFuture();
    }
}

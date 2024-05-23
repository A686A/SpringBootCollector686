package com.example.springbootdemo.service;

import java.util.concurrent.ExecutionException;

public interface ThreadService {

    void thread();
    void completableFuture() throws ExecutionException, InterruptedException;
}

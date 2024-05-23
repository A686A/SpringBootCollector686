package com.example.springbootdemo.service.Impl;

import com.example.springbootdemo.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Service
public class ThreadServiceImpl implements ThreadService {

    @Autowired
    private Executor threadPoolTaskExecutor;

    @Override
    public void thread() {
        Thread thread = new thread01();
        System.out.println("thread         id"+thread.getId());
        thread.start();
    }

    @Override
    public void completableFuture() throws ExecutionException, InterruptedException {

        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3); // 模拟耗时操作60秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Result of Task A";
        }, threadPoolTaskExecutor);

        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("thread       name"+ Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3); // 模拟耗时操作60秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Result of Task B";
        }, threadPoolTaskExecutor);

        // 等待所有任务完成
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futureA, futureB);

        List<String> list = new ArrayList<>();
        // 处理任务结果
        allFutures.thenAccept(v -> {
            try {
                list.add(futureA.get());
                list.add(futureB.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).get();
        System.out.println(list);
    }


    public static class thread01 extends Thread{
        @Override
        public void run() {
            System.out.println("thread      run"+ Thread.currentThread().getName());
        }
    }
}

package com.example.springbootdemo.common.exception;

import com.example.springbootdemo.common.model.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.example.springbootdemo.controller")
public class ExceptionControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handleVaildException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError) -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new Result();
    }


    @ExceptionHandler(value = Throwable.class)
    public Result handleException(Throwable throwable) {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxThrowable.class");
        return new Result();
    }

    @ExceptionHandler(value = Exception.class)
    public Result handleAllException(Exception e) {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxException.class" + e.getMessage());
        return new Result();
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    public Result handleAllException(HttpServerErrorException e) {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxHttpServerErrorException.class" + e.getMessage());
        return new Result();
    }
}

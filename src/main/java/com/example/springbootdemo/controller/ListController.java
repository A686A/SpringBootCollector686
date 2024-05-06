package com.example.springbootdemo.controller;


import com.example.springbootdemo.entity.Group;
import com.example.springbootdemo.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ListController {


    @Autowired
    ListService listService;

    //Mybatis嵌套查询结果集中包含简单数据类型集合或者多个List<T>的处理
    //http://localhost:8080/getGroupInfo
    @GetMapping("/getGroupInfo")
    public List<Group> getGroupInfo() {
        System.out.println("111111111111111111111111111111111111111111111111111111");
        return listService.getGroupInfo();
    }

    // Stream
//    Stream流执行完终端操作之后，无法再执行其他动作，否则会报状态异常，提示该流已经被执行操作或者被关闭，想要再次执行操作必须重新创建Stream流
//    一个流有且只能有一个终端操作，当这个操作执行后，流就被关闭了，无法再被操作，因此一个流只能被遍历一次，若想在遍历需要通过源数据在生成流。
//    终端操作的执行，才会真正开始流的遍历。如 count、collect 等

    // 用于过滤掉不满足条件的元素
    public void streamFilter() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        // 只保留偶数
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        // 输出: [2, 4, 6]
        System.out.println(evenNumbers);
    }

    // 将流中的每个元素转换为另一种形式
    public void streamMap() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        // 获取每个名字的长度
        List<Integer> nameLengths = names.stream()
                .map(String::length)
                .collect(Collectors.toList());
        // 输出: [5, 3, 7]
        System.out.println(nameLengths);
    }
}

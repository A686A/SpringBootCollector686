package com.example.springbootdemo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.mapper.OrderMapper;
import com.example.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserMapper usermapper;

    @Autowired
    private OrderMapper orderMapper;

    //http://localhost:8080/user
    @GetMapping("/user")
    public List<User> query() {
        List<User> userList = usermapper.find();
        return userList;
    }

    //使用继承的basemapper的方法
    @GetMapping("/user2")
    public List<User> query2() {
        List<User> userList = usermapper.selectList(null);
        return userList;
    }

    //使用继承的basemapper的方法
    @GetMapping("/user3")
    public List<User> query3() {

        return usermapper.selectAllUserAndOrders();
    }

    //  条件查询QueryWrapper
    @GetMapping("/user4")
    public List<User> findByCond(String username) {

//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getUsername,username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return usermapper.selectList(queryWrapper);
    }

    //  分页查询
//    List<Employee> result = employeeMapper.selectPage(new Page<>(1, 2),ew.between("id",1,20).eq("gender","F"));
    @GetMapping("/user/findByPage")
    public IPage findByPage() {
        //设置起始值及每页条数
        Page<User> page = new Page<>(0, 2);
        IPage iPage = usermapper.selectPage(page, null);
        return iPage;
    }

    @PostMapping("/insertUser")
    public int insertUser(User user) {
        usermapper.insert(user);
        return 1;
    }
}

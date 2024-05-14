package com.example.springbootdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo.common.constant.MessageId;
import com.example.springbootdemo.common.log.SLogger;
import com.example.springbootdemo.common.log.SLoggerFactory;
import com.example.springbootdemo.common.model.Result;
import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    private static final SLogger LOGGER = SLoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper usermapper;

    //http://localhost:8080/user
    @GetMapping("/user")
    public List<User> query() {
        LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), "error");
        return usermapper.find();
    }

    //使用继承的basemapper的方法
    @GetMapping("/user2")
    public List<User> query2() {
        return usermapper.selectList(null);
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

    //分页查询
    //List<Employee> result = employeeMapper.selectPage(new Page<>(1, 2),ew.between("id",1,20).eq("gender","F"));
    //http://localhost:8080/user/findByPage
    @GetMapping("/user/findByPage")
    public Result<IPage> findByPage(@RequestParam(defaultValue = "1") Integer current,
                                    @RequestParam(defaultValue = "10") Integer size) {
        //设置起始值及每页条数
        Page<User> page = new Page<>(current, size);
        IPage iPage = usermapper.selectPage(page, null);
        return Result.success(iPage);
    }


    //http://localhost:8080/user/findByName
    @GetMapping("/user/findByName")
    public Result<IPage> findByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username).orderByDesc("birthday");
        //设置起始值及每页条数
        Page<User> page = new Page<>(1, 15);
        IPage iPage = usermapper.selectPage(page, queryWrapper);
        return Result.success(iPage);
    }

    //
    //http://localhost:8080/userAdd
    @PostMapping("/userAdd")
    public Result<String> insertUser(@RequestBody User user) {
        System.out.println(user.getGender());
        String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        user.setBirthday(now);
        user.setPassword("6866");
        usermapper.insert(user);
        return Result.success("insert user success");
    }

    //
    //http://localhost:8080/addUserList
    @PostMapping("/addUserList")
    public Result<String> addUserList(@RequestBody List<User> users) {
        for (User user : users) {
            System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuserid" + user.getId());
            String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            user.setBirthday(now);
            user.setPassword("6866");
            usermapper.insert(user);
        }
        return Result.success("insert user success");
    }

    //单个删除用户
    //http://localhost:8080/user/{id}
    @DeleteMapping("/user/{id}")
    public int deleteUser(@PathVariable("id") long id) {
        usermapper.deleteById(id);
        return 1;
    }

    //批量删除用户
    //http://localhost:8080/user/deleteUserList
    @DeleteMapping("/user/deleteUserList")
    public Result<String> deleteUserList(@RequestBody List<Long> id) {
        usermapper.deleteBatchIds(id);
        return Result.success("delete user list success");
    }
}

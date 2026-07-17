package com.example.springbootdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo.common.constant.CodeConst;
import com.example.springbootdemo.common.constant.MessageId;
import com.example.springbootdemo.common.log.SLogger;
import com.example.springbootdemo.common.log.SLoggerFactory;
import com.example.springbootdemo.common.model.Result;
import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    private static final SLogger LOGGER = SLoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper usermapper;

    @GetMapping("/user")
    public List<User> query() {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + CodeConst.USER_ROLE);
        LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), "error");
        return usermapper.find();
    }

    @GetMapping("/user2")
    public List<User> query2() {
        return usermapper.selectList(null);
    }

    @GetMapping("/user3")
    public List<User> query3() {
        return usermapper.selectAllUserAndOrders();
    }

    @GetMapping("/user4")
    public List<User> findByCond(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return usermapper.selectList(queryWrapper);
    }

    @GetMapping("/user/findByPage")
    public Result<IPage> findByPage(@RequestParam(defaultValue = "1") Integer current,
                                    @RequestParam(defaultValue = "10") Integer size) {
        Page<User> page = new Page<>(current, size);
        IPage iPage = usermapper.selectPage(page, null);
        return Result.success(iPage);
    }

    @GetMapping("/user/findByName")
    public Result<IPage> findByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username).orderByDesc("birthday");
        Page<User> page = new Page<>(1, 15);
        IPage iPage = usermapper.selectPage(page, queryWrapper);
        return Result.success(iPage);
    }

    @GetMapping("/user/findByUsernameOrEmail")
    public Result<List<User>> findByUsernameOrEmail(@RequestParam(required = false) String username,
                                                    @RequestParam(required = false) String email) {
        if (!StringUtils.hasText(username) && !StringUtils.hasText(email)) {
            return Result.success(usermapper.selectList(null));
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(username) && StringUtils.hasText(email)) {
            queryWrapper.eq("username", username).or().eq("email", email);
        } else if (StringUtils.hasText(username)) {
            queryWrapper.eq("username", username);
        } else {
            queryWrapper.eq("email", email);
        }
        return Result.success(usermapper.selectList(queryWrapper));
    }

    @PostMapping("/userAdd")
    public Result<String> addUser(@RequestBody List<User> users) {
        LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), "addUser start");
        try {
            if (users == null || users.isEmpty()) {
                return Result.error("400", "user list cannot be empty");
            }

            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            for (User user : users) {
                if (user == null || !StringUtils.hasText(user.getUsername()) || !StringUtils.hasText(user.getEmail())) {
                    return Result.error("400", "username and email cannot be empty");
                }
                if (!StringUtils.hasText(user.getCreatedAt())) {
                    user.setCreatedAt(now);
                }
                usermapper.insert(user);
            }
            return Result.success("insert user success, count: " + users.size());
        } catch (Exception e) {
            LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), e, "addUser error");
            return Result.error("500", "insert user failed: " + e.getMessage());
        } finally {
            int size = users == null ? 0 : users.size();
            LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), "addUser end, count: " + size);
        }
    }

    @DeleteMapping("/user/{id}")
    public Result<String> deleteUser(@PathVariable("id") Long id) {
        LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), "deleteUser start, id: " + id);
        try {
            int deleted = usermapper.deleteById(id);
            if (deleted == 0) {
                return Result.error("404", "user not found");
            }
            return Result.success("delete user success");
        } catch (Exception e) {
            LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), e, "deleteUser error");
            return Result.error("500", "delete user failed: " + e.getMessage());
        } finally {
            LOGGER.info(MessageId.MESSAGE_ID_4000001.getLogMessageId(), "deleteUser end, id: " + id);
        }
    }

    @DeleteMapping("/user/deleteUserList")
    public Result<String> deleteUserList(@RequestBody List<Long> id) {
        usermapper.deleteBatchIds(id);
        return Result.success("delete user list success");
    }
}

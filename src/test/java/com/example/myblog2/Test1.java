package com.example.myblog2;

import com.example.myblog2.pojo.User;
import com.example.myblog2.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test1 {
    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        User user = userService.checkUser("shiyifan", "123456");

        System.out.println(user);
    }
}

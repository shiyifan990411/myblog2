package com.example.myblog2.service;

import com.example.myblog2.pojo.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User checkUser(String username, String password);
}

package com.example.myblog2.service.impl;

import com.example.myblog2.dao.UserRepository;
import com.example.myblog2.pojo.User;
import com.example.myblog2.service.UserService;
import com.example.myblog2.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}

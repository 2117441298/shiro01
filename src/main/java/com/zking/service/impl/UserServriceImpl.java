package com.zking.service.impl;

import com.zking.mapper.UserMapper;
import com.zking.model.User;
import com.zking.service.IUserServrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServriceImpl implements IUserServrice {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username) {
        return userMapper.login(username);
    }

    @Override
    public Set<String> getRole(String username) {
        return userMapper.getRole(username);
    }

    @Override
    public Set<String> getPermission(String username) {
        return userMapper.getPermission(username);
    }
}

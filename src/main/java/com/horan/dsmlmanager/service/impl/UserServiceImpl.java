package com.horan.dsmlmanager.service.impl;

import com.horan.dsmlmanager.dao.UserMapper;
import com.horan.dsmlmanager.entity.User;
import com.horan.dsmlmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;
    @Override
    public List<User> findAllUsers() {
        return userDao.selectAllUser();
    }
}

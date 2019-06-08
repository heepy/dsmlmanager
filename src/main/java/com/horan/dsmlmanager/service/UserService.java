package com.horan.dsmlmanager.service;

import com.horan.dsmlmanager.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAllUsers();
    User getSimpleUserById(int id);
    int addUser(User user);
}

package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.entity.User;
import com.horan.dsmlmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author horam
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/alluser")
    public List<User> getUsers() {
        return userService.findAllUsers();
    }
}

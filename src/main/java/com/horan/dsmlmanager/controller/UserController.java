package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.entity.User;
import com.horan.dsmlmanager.service.UserService;
import com.horan.dsmlmanager.utils.MyFileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author horam
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping(value = "/getuser")
    public User getUser(@RequestParam(value = "userId") int userId){
      return userService.getSimpleUserById(userId);
    }
    @PostMapping(value = "/register")
    public @ResponseBody Map<String,Object> addUser(@RequestBody User user){
     Map<String,Object> result=new HashMap<>();
     userService.addUser(user);
     int id=user.getId();
     String userDir=String.valueOf(id);
     System.out.println(userDir);
     MyFileUtils.mkUserdir(userDir);
     if(id!=0) {
         result.put("message","注册失败");
     }
        result.put("message", "注册成功！！");
        result.put("id",id);

     return result;
    }
    @PostMapping(value = "/updateuser")
    public @ResponseBody  Map<String,Object> updateUser(@RequestBody User user){
        Map<String,Object> result=new HashMap<>();
         userService.updateUser(user);
        return result;
    }
}

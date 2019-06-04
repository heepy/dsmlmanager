package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.entity.User;
import com.horan.dsmlmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ViewController {


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "static/index.html";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mv=new ModelAndView("login.html");
        return  mv;
    }
}

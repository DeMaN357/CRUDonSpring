package com.kirill.man.web.controller;

import com.kirill.man.web.model.User;
import com.kirill.man.web.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String printUser(ModelMap modelMap, Authentication authentication){
        User user = userService.getUserByName(authentication.getName());
        modelMap.addAttribute("user",user);
        return "user";
    }
}

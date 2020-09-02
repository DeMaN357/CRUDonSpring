package com.kirill.man.web.controller;

import com.kirill.man.web.model.User;
import com.kirill.man.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class MainUserController {

    private final UserService userService;

    @Autowired
    public MainUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "user")
    public String printUser(ModelMap modelMap) {
        List<User> userList = new ArrayList<>(userService.getAllUsers());
        modelMap.addAttribute("users", userList);
        return "users";
    }

    @GetMapping(value = "add")
    public String addUserGet() {
        return "add";
    }

    @PostMapping(value = "add")
    public String addUserPost(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping(value = "update/{id}")
    public String updateUserGet(ModelMap modelMap, @PathVariable Long id) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "update";
    }

    @PostMapping(value = "update")
    public String updateUserPost(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/user";
    }

    @GetMapping(value = "delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }
}

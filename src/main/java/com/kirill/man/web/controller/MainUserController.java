package com.kirill.man.web.controller;

import com.kirill.man.web.model.User;
import com.kirill.man.web.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class MainUserController {

    private final CarService carService;

    @Autowired
    public MainUserController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "user")
    public String printUser(ModelMap modelMap, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        List<User> userList = new ArrayList<>(carService.getAllUsers());
        modelMap.addAttribute("users", userList);
        return "users";
    }

    @GetMapping(value = "add")
    public String addUserGet() {
        return "add";
    }

    @PostMapping(value = "add")
    public String addUserPost(@ModelAttribute("user") User user) {
        carService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping(value = "update/{id}")
    public String updateUserGet(ModelMap modelMap, @PathVariable Long id) {
        User user = carService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "update";
    }

    @PostMapping(value = "update")
    public String updateUserPost(@ModelAttribute User user) {
        carService.updateUser(user);
        return "redirect:/user";
    }

    @GetMapping(value = "delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        carService.deleteUser(id);
        return "redirect:/user";
    }
}

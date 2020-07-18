package com.kirill.man.web.controller;

import com.kirill.man.web.model.User;
import com.kirill.man.web.service.RoleService.RoleService;
import com.kirill.man.web.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
    public String addUserPost(@ModelAttribute User user, @RequestParam(value = "rolesFromH") String[] roles) {
        user.setRoles(roleService.getRoles(roles));
        userService.addUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping(value = "update/{id}")
    public String updateUserGet(ModelMap modelMap, @PathVariable Long id) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "update";
    }

    @PostMapping(value = "update")
    public String updateUserPost(@ModelAttribute User user, @RequestParam(value = "rolesFromH") String[] roles) {
        user.setRoles(roleService.getRoles(roles));
        userService.updateUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping(value = "delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }
}

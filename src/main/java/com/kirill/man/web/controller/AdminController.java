package com.kirill.man.web.controller;

import com.kirill.man.web.model.User;
import com.kirill.man.web.service.RoleService.RoleService;
import com.kirill.man.web.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @GetMapping(value = "admin")
    public String printUser(ModelMap modelMap, Authentication authentication) {
        List<User> userList = new ArrayList<>(userService.getAllUsers());
        modelMap.addAttribute("users", userList);
        modelMap.addAttribute("user", (User)authentication.getPrincipal());
        return "admin";
    }

    @PostMapping(value = "add")
    public String addUserPost(@ModelAttribute User user, @RequestParam(value = "rolesFromH") String[] roles) {
        user.setRoles(roleService.getRoles(roles));
        userService.addUser(user);
        return "redirect:/admin/admin";
    }

    @GetMapping(value = "update")
    public String updateUserGet(ModelMap modelMap, @RequestParam(value = "id") Long id) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "update";
    }

    @PostMapping(value = "update")
    public String updateUserPost(@ModelAttribute User user, @RequestParam(value = "rolesFromH") String[] roles) {
        user.setRoles(roleService.getRoles(roles));
        userService.updateUser(user);
        return "redirect:/admin/admin";
    }

    @GetMapping(value = "delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/admin";
    }
}

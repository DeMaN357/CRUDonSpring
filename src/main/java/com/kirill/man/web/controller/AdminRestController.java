package com.kirill.man.web.controller;

import com.kirill.man.web.model.Role;
import com.kirill.man.web.model.User;
import com.kirill.man.web.service.RoleService.RoleService;
import com.kirill.man.web.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/adminRest")
public class AdminRestController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "/userAuth")
    public User getUserAuth(Authentication authentication){
        return (User) authentication.getPrincipal();
    }


    @GetMapping(value = "/allUsers")
    public ResponseEntity<List<User>> printUsers(){
        List<User> userList = new ArrayList<>(userService.getAllUsers());
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping(value = "/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping(value = "/getAllRoles")
    public ResponseEntity<Set<Role>> getAllRoles(){
        Set<Role> allRoles = roleService.getRoles(new String[]{"ADMIN", "USER"});
        return ResponseEntity.ok().body(allRoles);
    }

    @PostMapping(value = "/updateUser")
    public void updateUserJS(@RequestBody User user){
        System.out.println(user.getRoles());
        userService.updateUser(user);

    }
}

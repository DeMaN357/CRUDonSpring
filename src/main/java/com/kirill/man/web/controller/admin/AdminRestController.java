package com.kirill.man.web.controller.admin;

import com.kirill.man.web.dto.UserDTO;
import com.kirill.man.web.model.User;
import com.kirill.man.web.service.RoleService.RoleService;
import com.kirill.man.web.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<User> getUserAuth(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = new ArrayList<>(userService.getAllUsers());
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping(value = "/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id, ModelMap modelMap) {
        Long id23 = (Long) modelMap.getAttribute("id");
        System.out.println(id23);
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PutMapping(value = "/updateUser")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getFirst_name(),
                userDTO.getLast_name(),
                userDTO.getPassword(),
                userDTO.getAge(),
                userDTO.getEmail(),
                roleService.getRoles(userDTO.getRoles())
        );
        userService.updateUser(user);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity<HttpStatus> deleteUser(@RequestBody Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping(value = "/addUser")
    public ResponseEntity<HttpStatus> addUser(@RequestBody UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getFirst_name(),
                userDTO.getLast_name(),
                userDTO.getPassword(),
                userDTO.getAge(),
                userDTO.getEmail(),
                roleService.getRoles(userDTO.getRoles())
        );
        userService.addUser(user);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}

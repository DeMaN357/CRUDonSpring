package com.kirill.man.web.controller.user;

import com.kirill.man.web.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/userRest")
public class UserRestController {

    @GetMapping(value = "/getUser")
    public ResponseEntity<User> getTableOfUser(Authentication authentication) {
        return ResponseEntity.ok().body((User) authentication.getPrincipal());
    }

}

package com.kirill.man.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class LoginController {
    @GetMapping(value = "login")
    public String getLoginPage(Authentication authentication) {
        if (authentication != null) {
            if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
                return "redirect:/admin/admin";
            } else {
                return "redirect:/user";
            }
        } else {
            return "login";
        }
    }

    @PostMapping(value = "login")
    public String redirectForAnotherPage() {
        return "redirect:/admin/admin";
    }

}

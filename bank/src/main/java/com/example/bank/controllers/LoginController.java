package com.example.bank.controllers;

import com.example.bank.models.User;
import com.example.bank.models.enums.Role;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getPage(@AuthenticationPrincipal User user){
        if(user!=null){
            if(user.getRoles().contains(Role.ADMIN)) {
                return "redirect:/admin";
            }
            else {
                return "redirect:/client";
            }
        }
        return "login";
    }
}

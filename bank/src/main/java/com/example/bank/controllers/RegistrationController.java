package com.example.bank.controllers;

import com.example.bank.models.TempRegistration;
import com.example.bank.models.User;
import com.example.bank.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping
    public String getPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            return "redirect:/";
        }
        model.addAttribute("tempRegistration", new TempRegistration());
        return "registration";
    }

    @PostMapping()
    public String registration(TempRegistration temp, Map<String, Object> model) {
        return userService.registration(temp, model);
    }

    @GetMapping("/approve/{token}")
    public String approveRegistration(@PathVariable("token") String token){
        return userService.approveRegistration(token);
    }
}

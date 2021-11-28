package com.example.bank.controllers;

import com.example.bank.models.User;
import com.example.bank.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public String getMainPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("cards", user.getClientDetails().getCards());
        model.addAttribute("accounts", user.getClientDetails().getAccounts());
        return "client/client";
    }
}

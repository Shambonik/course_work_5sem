package com.example.bank.controllers;

import com.example.bank.dto.TransactionDTO;
import com.example.bank.dto.enums.CardAccountEnum;
import com.example.bank.models.User;
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

    @GetMapping
    public String getMainPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("cards", user.getClientDetails().getCards());
        model.addAttribute("accounts", user.getClientDetails().getAccounts());
        return "client/client";
    }

    @GetMapping("/transaction")
    public String getTransactionPage(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("transaction", new TransactionDTO());
        model.addAttribute("cardOrAccount", CardAccountEnum.values());
        model.addAttribute("cards", user.getClientDetails().getCards());
        model.addAttribute("accounts", user.getClientDetails().getAccounts());
        return "client/transaction";
    }
}

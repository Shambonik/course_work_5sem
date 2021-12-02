package com.example.bank.controllers;

import com.example.bank.dto.*;
import com.example.bank.models.AccountType;
import com.example.bank.models.ClientDetails;
import com.example.bank.services.AccountService;
import com.example.bank.services.CardService;
import com.example.bank.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ClientService clientService;
    private final CardService cardService;
    private final AccountService accountService;

    @GetMapping("/clients")
    public String getClientsPage(Model model, HttpServletRequest request) {
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                model.addAttribute(entry.getKey(), entry.getValue());
            }
        }
        if (!model.containsAttribute("list")) {
            model.addAttribute("list", clientService.getClientList());
        }
        if (!model.containsAttribute("findByPassportNumber")) {
            model.addAttribute("findByPassportNumber", new FindByPassportNumberDTO());
        }
        return "admin/clients/clients";
    }


    @GetMapping("/clients/find_by_passport_number")
    public String filterByPassportNumber(FindByPassportNumberDTO findByPassportNumberDTO, RedirectAttributes redirectAttributes) {
        return clientService.filterByPassportNumber(findByPassportNumberDTO, redirectAttributes);
    }

    @GetMapping("/clients/add_client")
    public String getAddClientPage(Model model) {
        model.addAttribute("client", new AddClientDTO());
        return "admin/clients/add_client";
    }

    @PostMapping("/clients/add_client")
    public String addClient(AddClientDTO dto){
        return clientService.addClient(dto);
    }

    @GetMapping("/clients/{id}/cards")
    public String getCardsPage(@PathVariable("id") long id, Model model, HttpServletRequest request){
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                model.addAttribute(entry.getKey(), entry.getValue());
            }
        }
        if (!model.containsAttribute("client")) {
            ClientDetails client = clientService.getClientById(id);
            model.addAttribute("client", client);
        }
        if (!model.containsAttribute("findByNumber")){
            model.addAttribute("findByNumber", new FindCardByNumberDTO());
        }
        return "admin/clients/cards/cards";
    }

    @GetMapping("/clients/{id}/cards/find_by_number")
    public String filterCardsByNumber(@PathVariable("id") long id, FindCardByNumberDTO dto, RedirectAttributes redirectAttributes) {
        return clientService.findCardByNumber(id, dto, redirectAttributes);
    }

    @GetMapping("/clients/{id}/cards/add_card")
    public String getAddCardPage(@PathVariable("id") long id, Model model){
        model.addAttribute("clientId", id);
        model.addAttribute("card", new AddCardDTO());
        return "admin/clients/cards/add_card";
    }

    @PostMapping("/clients/{id}/cards/add_card")
    public String addCard(@PathVariable("id") long id, AddCardDTO dto){
        return clientService.addCard(id, dto);
    }

    @GetMapping("/cards/{id}/top_up")
    public String getTopUpCardPage(@PathVariable("id") long id, Model model){
        model.addAttribute("card", cardService.findCardById(id));
        model.addAttribute("topUp", new TopUpDTO());
        return "/admin/clients/cards/top_up_card";
    }

    @PutMapping("/cards/{id}/top_up")
    public String topUpCard(@PathVariable("id") long id, TopUpDTO dto){
        cardService.topUpCard(id, dto);
        return "redirect:/admin/cards/"+id+"/top_up";
    }


    @GetMapping("/clients/{id}/accounts")
    public String getAccountsPage(@PathVariable("id") long id, Model model, HttpServletRequest request){
        ClientDetails client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "admin/clients/accounts/accounts";
    }

    @GetMapping("/clients/{id}/accounts/add_account")
    public String getAddAccountPage(@PathVariable("id") long id, Model model){
        model.addAttribute("clientId", id);
        model.addAttribute("types", accountService.findAllAccountTypes());
        return "admin/clients/accounts/add_account";
    }

    @PostMapping("/clients/{id}/accounts/add_account/{accType}")
    public String addAccount(@PathVariable("id") long id, @PathVariable("accType") long accType){
        return clientService.addAccount(id, accType);
    }

    @GetMapping("/add_account_type")
    public String getAddAccountTypePage(Model model){
        model.addAttribute("accountType", new AccountType());
        return "admin/accountTypes/add_account_type";
    }

    @PostMapping("/add_account_type")
    public String addAccountType(AccountType type){
        return accountService.addAccountType(type);
    }

}

package com.example.bank.controllers;

import com.example.bank.dto.FindByPassportNumberDTO;
import com.example.bank.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ClientService clientService;

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

}

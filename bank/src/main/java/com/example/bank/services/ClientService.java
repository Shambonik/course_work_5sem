package com.example.bank.services;

import com.example.bank.dto.*;
import com.example.bank.models.Account;
import com.example.bank.models.AccountType;
import com.example.bank.models.Card;
import com.example.bank.models.ClientDetails;
import com.example.bank.repositories.CardRepo;
import com.example.bank.repositories.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepo clientRepo;
    private final CardRepo cardRepo;
    private final AccountService accountService;

    public List<ClientListDTO> getClientList() {
        return clientListToDTO(clientRepo.findAll());
    }

    public ClientDetails getClientById(Long id) {
        return clientRepo.findClientDetailsById(id);
    }

    public String filterByPassportNumber(FindByPassportNumberDTO findByPassportNumberDTO, RedirectAttributes redirectAttributes) {
        String passportNumber = findByPassportNumberDTO.getPassportNumber().replace(" ", "");
        if (!"".equals(passportNumber)) {
            redirectAttributes.addFlashAttribute("list", clientListToDTO(clientRepo.findByPassportNumber(passportNumber)));
            redirectAttributes.addFlashAttribute("findByPassportNumber", findByPassportNumberDTO);
        }
        return "redirect:/admin/clients";
    }

    public String addClient(AddClientDTO dto) {
        ClientDetails client = new ClientDetails();
        client.setFirstname(dto.getFirstname());
        client.setLastname(dto.getLastname());
        client.setMiddlename(dto.getMiddlename());
        client.setPassportNumber(dto.getPassportNumber());
        client.setEmail(dto.getEmail());
        clientRepo.save(client);
        return "redirect:/admin/clients";
    }

    public String findCardByNumber(Long id, FindCardByNumberDTO dto, RedirectAttributes redirectAttributes) {
        ClientDetails client = getClientById(id);
        String number = dto.getCardNumber();
        if (!"".equals(number)) {
            client.setCards(client.getCards().stream()
                    .filter(card -> card.getNumber().equals(number))
                    .collect(Collectors.toSet()));
            redirectAttributes.addFlashAttribute("client", client);
            redirectAttributes.addFlashAttribute("findByNumber", dto);
        }
        return "redirect:/admin/clients/" + id + "/cards";
    }

    public String addCard(Long id, AddCardDTO dto) {
        Card card = new Card();
        card.setNumber(dto.getNumber());
        card.setCvv((new Random()).nextInt(900) + 100);
        card.setMoney(0);
        String[] dateStrings = dto.getValidDate().split("-");
        LocalDate date = LocalDate.of(
                Integer.parseInt(dateStrings[0]),
                Integer.parseInt(dateStrings[1]),
                Integer.parseInt(dateStrings[2])
        );
        card.setValidDate(date);
        card.setActive(true);
        card.setClient(getClientById(id));
        cardRepo.save(card);
        return "redirect:/admin/clients/" + id + "/cards";
    }

    public String addAccount(long id, long accType) {
        Account account = new Account();
        AccountType accountType = accountService.findAccountTypeById(accType);
        ClientDetails client = getClientById(id);
        int accountListSize = accountService.findAllAccountsByClientAndAccountType(client, accountType).size();
        String name = accountType.getName() + (accountListSize > 0 ? " " + accountListSize : "");
        account.setName(name);
        account.setAccountType(accountType);
        account.setClient(client);
        account.setActive(true);
        account.setMoney(0);
        accountService.saveAccount(account);
        return "redirect:/admin/clients/" + id + "/accounts";
    }

    private List<ClientListDTO> clientListToDTO(List<ClientDetails> clientDetails) {
        return clientDetails.stream()
                .map((client) -> {
                    ClientListDTO dto = new ClientListDTO();
                    StringBuilder fullName = new StringBuilder();
                    fullName.append(client.getFirstname())
                            .append(" ")
                            .append(client.getLastname());
                    if (client.getMiddlename() != null) {
                        fullName.append(" ")
                                .append(client.getMiddlename());
                    }
                    dto.setFullName(fullName.toString());
                    dto.setPassportNumber(client.getPassportNumber());
                    dto.setId(client.getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

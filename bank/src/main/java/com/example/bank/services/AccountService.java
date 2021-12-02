package com.example.bank.services;

import com.example.bank.dto.TopUpDTO;
import com.example.bank.models.Account;
import com.example.bank.models.AccountType;
import com.example.bank.models.Card;
import com.example.bank.models.ClientDetails;
import com.example.bank.repositories.AccountRepo;
import com.example.bank.repositories.AccountTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepo accountRepo;
    private final AccountTypeRepo accountTypeRepo;

    public Account findAccountById(Long id) {
        return accountRepo.findAccountById(id);
    }

    public List<AccountType> findAllAccountTypes() {
        return accountTypeRepo.findAll();
    }


    public List<Account> findAllAccountsByClientAndAccountType(ClientDetails client, AccountType accountType) {
        return accountRepo.findAllByClientAndAccountType(client, accountType);
    }

    public AccountType findAccountTypeById(Long id) {
        return accountTypeRepo.findAccountTypeById(id);
    }

    public void saveAccount(Account account) {
        accountRepo.save(account);
    }

    public String addAccountType(AccountType type){
        accountTypeRepo.save(type);
        return "redirect:/admin";
    }
}

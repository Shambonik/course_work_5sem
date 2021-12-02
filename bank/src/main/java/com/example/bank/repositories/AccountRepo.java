package com.example.bank.repositories;

import com.example.bank.models.Account;
import com.example.bank.models.AccountType;
import com.example.bank.models.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findAccountById(Long id);

    List<Account> findAllByClientAndAccountType(ClientDetails client, AccountType accountType);
}

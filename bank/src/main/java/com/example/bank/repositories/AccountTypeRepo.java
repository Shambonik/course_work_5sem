package com.example.bank.repositories;

import com.example.bank.models.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTypeRepo extends JpaRepository<AccountType, Long> {
    List<AccountType> findAll();

    AccountType findAccountTypeById(Long id);
}

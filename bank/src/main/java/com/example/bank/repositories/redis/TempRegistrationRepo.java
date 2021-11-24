package com.example.bank.repositories.redis;

import com.example.bank.models.TempRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempRegistrationRepo extends CrudRepository<TempRegistration, String> {
    TempRegistration findTempRegistrationByCardNumber(String cardNumber);
}
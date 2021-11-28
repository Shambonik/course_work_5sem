package com.example.bank.repositories;

import com.example.bank.models.Card;
import com.example.bank.models.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    Card findCardByNumber(String number);
    Card findCardById(Long id);
}
